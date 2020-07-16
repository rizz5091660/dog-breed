package com.yabonza.dog.breed.api.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yabonza.dog.breed.api.config.DogBreedConfig;

@Component
public class FileUtil {
	private static final Logger log = LoggerFactory.getLogger(FileUtil.class);
	@Autowired
	DogBreedConfig dogBreedConfig;
	
	public File save(String imageUrl, String imageName) {
		 try {
			FileUtils.copyURLToFile(
					  new URL(imageUrl), 
					  new File(dogBreedConfig.getFileTempFolder()+imageName));
			return new File(dogBreedConfig.getFileTempFolder()+imageName);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}
	
	public void delete(File file) {
		FileUtils.deleteQuietly(file);
	}

}
