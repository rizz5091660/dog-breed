package com.yabonza.dog.breed.api.util;

import org.springframework.stereotype.Component;

@Component
public class StringUtil {

	public String getBreedName(String message){
		int lastIndex =message.lastIndexOf("/");
		String dogNamePath = message.substring(0, lastIndex);
		lastIndex =message.lastIndexOf("/");
		lastIndex =dogNamePath.lastIndexOf("/");
		String breedPath = dogNamePath.substring(0, lastIndex);
		return (message.substring(breedPath.length(), dogNamePath.length())).replace("/", "");
	}
	
	public String getImageName(String message){
		int lastIndex =message.lastIndexOf("/");
		String imageNamePath = message.substring(0, lastIndex);
		return (message.substring(imageNamePath.length(), message.length())).replace("/", "");
	}
}
