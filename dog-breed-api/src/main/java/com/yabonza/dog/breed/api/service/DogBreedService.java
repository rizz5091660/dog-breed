package com.yabonza.dog.breed.api.service;

import org.springframework.http.ResponseEntity;

public interface DogBreedService {
	public ResponseEntity<Object> generate();
	public ResponseEntity<Object> get(String id);
	public ResponseEntity<Object> delete(String id);
	public ResponseEntity<Object> getAll();
	public ResponseEntity<Object> getByName(String name);

}
