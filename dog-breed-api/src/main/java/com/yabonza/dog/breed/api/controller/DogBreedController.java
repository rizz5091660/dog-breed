package com.yabonza.dog.breed.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yabonza.dog.breed.api.service.DogBreedService;

@RestController
@RequestMapping("/dogbreed")
public class DogBreedController {
	@Autowired
	DogBreedService dogBreedService;
	
	@GetMapping
	@ResponseBody
	public ResponseEntity<Object> generate(){
		return dogBreedService.generate();
	}

}
