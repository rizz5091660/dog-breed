package com.yabonza.dog.breed;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yabonza.dog.breed.model.DogBreed;

@Repository
public interface DogBreedRepository extends CrudRepository<DogBreed, String> {

}
