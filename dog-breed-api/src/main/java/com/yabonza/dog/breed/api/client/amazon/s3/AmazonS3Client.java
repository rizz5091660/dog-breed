package com.yabonza.dog.breed.api.client.amazon.s3;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.yabonza.dog.breed.api.config.DogBreedConfig;
import com.yabonza.dog.breed.api.util.StringUtil;


@Service
public class AmazonS3Client {

	@Autowired
	DogBreedConfig dogBreedConfig;
	private static final Logger log = LoggerFactory.getLogger(AmazonS3Client.class);
	@Autowired
	StringUtil stringUtil;
	
	public String upload(File file, String image) throws Exception {	
		try {
			AWSCredentials credentials = new BasicAWSCredentials(dogBreedConfig.getAwsAccessKey(),dogBreedConfig.getAwsSecretKey());
			AmazonS3 s3client = AmazonS3ClientBuilder
					.standard()
					.withCredentials(new AWSStaticCredentialsProvider(credentials))
					.withRegion(Regions.AP_SOUTHEAST_2)
					.build();		
			s3client.putObject(new PutObjectRequest(dogBreedConfig.getAwsBucketName(), dogBreedConfig.getAwsBucketFolder()+"/"+image, file)
					.withCannedAcl(CannedAccessControlList.PublicRead));
			return dogBreedConfig.getAwsCdnUrl()+dogBreedConfig.getAwsBucketFolder()+"/"+image;
		}catch(Exception e) {
			log.error(e.getMessage());
			throw e;	
		}
	}
	
	public void delete(String s3ImageBucketPath) throws Exception {	
		try {
			AWSCredentials credentials = new BasicAWSCredentials(dogBreedConfig.getAwsAccessKey(),dogBreedConfig.getAwsSecretKey());
			AmazonS3 s3client = AmazonS3ClientBuilder
					.standard()
					.withCredentials(new AWSStaticCredentialsProvider(credentials))
					.withRegion(Regions.AP_SOUTHEAST_2)
					.build();		
			s3client.deleteObject(dogBreedConfig.getAwsBucketName(), dogBreedConfig.getAwsBucketFolder()+"/"+stringUtil.getImageName(s3ImageBucketPath));
		}catch(Exception e) {
			log.error(e.getMessage());
			throw e;	
		}

	}

}
