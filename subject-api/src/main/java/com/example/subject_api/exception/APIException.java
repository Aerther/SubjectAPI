package com.example.subject_api.exception;

import org.springframework.http.HttpStatus;

public class APIException {
	private final String message;
	private final Throwable cause;
	private final HttpStatus httpStatus;
	
	public APIException(String message, Throwable cause, HttpStatus httpStatus) {
		this.message = message;
		this.cause = cause;
		this.httpStatus = httpStatus;
	}

	// Getters and Setters
	
	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public Throwable getCause() {
		return this.cause;
	}
}
