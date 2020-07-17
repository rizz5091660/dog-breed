package com.yabonza.dog.breed.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@Data
public class DogBreedConfig {
	@Value("${aws.access.key}")
	private String awsAccessKey;
	@Value("${aws.secret.key}")
	private String awsSecretKey;
	@Value("${aws.bucket.name}")
	private String awsBucketName;
	@Value("${aws.bucket.folder}")
	private String awsBucketFolder;
	@Value("${aws.cdn.url}")
	private String awsCdnUrl;
	@Value("${file.temp.folder}")
	private String fileTempFolder;
	@Value("${dog.ceo.url}")
	private String dogCeoUrl;
	
}
