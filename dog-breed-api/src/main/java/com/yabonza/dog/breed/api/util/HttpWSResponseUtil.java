package com.yabonza.dog.breed.api.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HttpWSResponseUtil {
	
	public static ResponseEntity<Object> generateResponse(HttpStatus httpStatus,Object object){
		return ResponseEntity.status(httpStatus).body(object);
		
	}
}
