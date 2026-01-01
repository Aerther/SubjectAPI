package com.example.subject_api.response;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
	public static ResponseEntity<Object> responseBuilder(
		String message,
		HttpStatus httpStatus,
		Object object
	) {
		Map<String, Object> response = new HashMap<>();
		
		response.put("message", message);
		response.put("status", httpStatus.value());
		response.put("data", object);
		response.put("date", LocalDateTime.now());
		
		return new ResponseEntity<>(response, httpStatus);
	}
	
	public static ResponseEntity<Object> responseBuilder(
		String message,
		HttpStatus httpStatus
	) {
		Map<String, Object> response = new HashMap<>();
			
		response.put("message", message);
		response.put("status", httpStatus.value());
		response.put("date", LocalDateTime.now());
			
		return new ResponseEntity<>(response, httpStatus);
	}
	
	public static ResponseEntity<Object> responseBuilder(
		String message,
		HttpStatus httpStatus,
		List<Object> objects
	) {
		Map<String, Object> response = new HashMap<>();
		
		response.put("message", message);
		response.put("status", httpStatus.value());
		response.put("data", objects);
		response.put("date", LocalDateTime.now());
			
		return new ResponseEntity<>(response, httpStatus);
	}
}
