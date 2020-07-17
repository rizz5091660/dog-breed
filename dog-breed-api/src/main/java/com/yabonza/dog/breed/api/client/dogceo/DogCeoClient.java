package com.yabonza.dog.breed.api.client.dogceo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;

import com.yabonza.dog.breed.api.config.DogBreedConfig;
import com.yabonza.dog.breed.model.DogCeo;

@Component
public class DogCeoClient {
	@Autowired 
	DogBreedConfig dogBreedConfig;
	
	public DogCeo get() {
		RestTemplateBuilder builder = new RestTemplateBuilder();		
		return builder.build().getForObject(dogBreedConfig.getDogCeoUrl(), DogCeo.class);
	}
	
}
