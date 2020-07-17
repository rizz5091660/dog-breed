package com.yabonza.dog.breed.api.service;

import java.io.File;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.yabonza.dog.breed.api.client.amazon.s3.AmazonS3Client;
import com.yabonza.dog.breed.api.client.dogceo.DogCeoClient;
import com.yabonza.dog.breed.api.util.FileUtil;
import com.yabonza.dog.breed.api.util.HttpWSResponseUtil;
import com.yabonza.dog.breed.api.util.StringUtil;
import com.yabonza.dog.breed.model.DogBreed;
import com.yabonza.dog.breed.model.DogCeo;

@Service
@Transactional
public class DogBreedServiceImpl implements DogBreedService {
	@Autowired
	DogBreedRepository dogBreedRepository;
	@Autowired
	AmazonS3Client amazonS3Client; 
	@Autowired
	DogCeoClient dogCeoClient;
	@Autowired
	FileUtil fileUtil;
	@Autowired
	StringUtil stringUtil;
	
	private static final Logger log = LoggerFactory.getLogger(DogBreedServiceImpl.class);

	public ResponseEntity<Object> generate() {
		DogBreed dogBreed = new DogBreed();
		try {
			DogCeo dogCeo = dogCeoClient.get();
			fetchDogGeneratorResponse(dogBreed, dogCeo);
			File file = fileUtil.save(dogCeo.getMessage(),stringUtil.getImageName(dogCeo.getMessage()));
			String objectUrl = amazonS3Client.upload(file, stringUtil.getImageName(dogCeo.getMessage()));
			dogBreed.setS3BucketPath(objectUrl);
			dogBreedRepository.save(dogBreed);
			fileUtil.delete(file);
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return HttpWSResponseUtil.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "System Error");
		}
		return HttpWSResponseUtil.generateResponse(HttpStatus.OK, dogBreed);
	}

	private void fetchDogGeneratorResponse(DogBreed db, DogCeo dogCeo) {
		db.setId(UUID.randomUUID().toString());
		db.setName(stringUtil.getBreedName(dogCeo.getMessage()));
		db.setUploadedDt(new Date());
	}
	

	public ResponseEntity<Object> get(String id) {
		try {			
			return HttpWSResponseUtil.generateResponse(HttpStatus.OK, dogBreedRepository.findById(id));
		}catch(Exception e) {
			log.error(e.getMessage());
			return HttpWSResponseUtil.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "System Error");
		}
	}

	public ResponseEntity<Object> delete(String id) {
		try {
			Optional<DogBreed> dogBreed = dogBreedRepository.findById(id);
			if(dogBreed.isPresent()) {
				amazonS3Client.delete(dogBreed.get().getS3BucketPath());
				dogBreedRepository.deleteById(id);
			}			
			return HttpWSResponseUtil.generateResponse(HttpStatus.OK, "OK" );
		}catch(Exception e) {
			log.error(e.getMessage());
			return HttpWSResponseUtil.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "System Error");
		}
	}

	@Override
	public ResponseEntity<Object> getAll() {
		 return HttpWSResponseUtil.generateResponse(HttpStatus.OK, dogBreedRepository.findAll() );
	}

	@Override
	public ResponseEntity<Object> getByName(String name) {
		name=name!=null?name.toLowerCase():name;
		return HttpWSResponseUtil.generateResponse(HttpStatus.OK, dogBreedRepository.findByName(name));
	}
	

}
