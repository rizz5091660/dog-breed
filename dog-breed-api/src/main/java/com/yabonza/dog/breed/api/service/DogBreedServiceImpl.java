package com.yabonza.dog.breed.api.service;

import java.io.File;
import java.util.Date;
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
import com.yabonza.dog.breed.model.DogBreed;
import com.yabonza.dog.breed.model.DogCeo;

@Service
@Transactional
public class DogBreedServiceImpl implements DogBreedService {
	@Autowired
	DogBreedRepository2 dogBreedRepository;
	@Autowired
	AmazonS3Client amazonS3Client; 
	@Autowired
	DogCeoClient dogCeoClient;
	@Autowired
	FileUtil fileUtil;
	
	private static final Logger log = LoggerFactory.getLogger(DogBreedServiceImpl.class);

	public ResponseEntity<Object> generate() {
		DogBreed dogBreed = new DogBreed();
		try {
			DogCeo dogCeo = dogCeoClient.get();
			populateDogBreed(dogBreed, dogCeo);
			File file = fileUtil.save(dogCeo.getMessage(),getImageName(dogCeo.getMessage()));
			String objectUrl = amazonS3Client.uploadS3(file, getImageName(dogCeo.getMessage()));
			dogBreed.setS3BucketPath(objectUrl);
			dogBreedRepository.save(dogBreed);
			fileUtil.delete(file);
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return HttpWSResponseUtil.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, "System Error");
		}
		return HttpWSResponseUtil.generateResponse(HttpStatus.OK, dogBreed);
	}

	private void populateDogBreed(DogBreed db, DogCeo dogCeo) {
		db.setId(UUID.randomUUID().toString());
		db.setName(getBreedName(dogCeo.getMessage()));
		db.setUploadedDt(new Date());
	}
	
	
	private String getBreedName(String message){
		int lastIndex =message.lastIndexOf("/");
		String dogNamePath = message.substring(0, lastIndex);
		lastIndex =message.lastIndexOf("/");
		lastIndex =dogNamePath.lastIndexOf("/");
		String breedPath = dogNamePath.substring(0, lastIndex);
		return (message.substring(breedPath.length(), dogNamePath.length())).replace("/", "");
	}
	
	private String getImageName(String message){
		int lastIndex =message.lastIndexOf("/");
		String imageNamePath = message.substring(0, lastIndex);
		return (message.substring(imageNamePath.length(), message.length())).replace("/", "");
	}
	
	

}
