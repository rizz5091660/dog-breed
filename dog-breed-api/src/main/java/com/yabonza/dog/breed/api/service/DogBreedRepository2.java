package com.yabonza.dog.breed.api.service;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yabonza.dog.breed.model.DogBreed;

@Repository
public interface DogBreedRepository2 extends CrudRepository<DogBreed, String> {

}
