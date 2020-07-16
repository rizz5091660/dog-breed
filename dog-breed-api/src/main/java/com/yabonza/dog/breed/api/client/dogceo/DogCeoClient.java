package com.yabonza.dog.breed.api.client.dogceo;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;

import com.yabonza.dog.breed.model.DogCeo;

@Component
public class DogCeoClient {
	public DogCeo get() {
		RestTemplateBuilder builder = new RestTemplateBuilder();		
		return builder.build().getForObject("https://dog.ceo/api/breeds/image/random", DogCeo.class);
	}
	
}
