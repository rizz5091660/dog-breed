package com.yabonza.dog.breed.api.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HttpWSResponseUtil {

	public static final String RC_OKAY = "0";
	public static final String RC_SYSTEM_ERROR = "1";
	public static final String RC_DUPLICATE_ACCOUNT = "1001";
	public static final String RC_AUTHENTICATION_ERROR = "1001";
	
	public static int HTTP_RSC_OK =200;
	public static int HTTP_RSC_ERR =500;
	public static int APP_RSC_ERR_LOGIN =301;
	public static int APP_RSC_ERR_ACC_CREATE =311;
	public static int APP_RSC_ERR_ACC_DUPLICATE =312;
	
	public static ResponseEntity<Object> generateResponse(HttpStatus httpStatus,Object object){
		return ResponseEntity.status(httpStatus).body(object);
		
	}
}
