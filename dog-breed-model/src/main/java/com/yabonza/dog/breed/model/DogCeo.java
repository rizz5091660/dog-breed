package com.yabonza.dog.breed.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DogCeo {
	private String message;
	private String status;

}
