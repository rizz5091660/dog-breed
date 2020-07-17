package com.yabonza.dog.breed.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping(value="/{id}")
	@ResponseBody
	public ResponseEntity<Object> get(@PathVariable String id){
		return dogBreedService.get(id);
	}
	
	@DeleteMapping(value="/{id}")
	@ResponseBody
	public ResponseEntity<Object> delete(@PathVariable String id){
		return dogBreedService.delete(id);
	}

	
	@GetMapping(value="/get/all")
	@ResponseBody
	public ResponseEntity<Object> getAll(){
		return dogBreedService.getAll();
	}
	
	@GetMapping(value="/get/name/{name}")
	@ResponseBody
	public ResponseEntity<Object> getByName(@PathVariable String name){
		return dogBreedService.getByName(name);
	}

}
