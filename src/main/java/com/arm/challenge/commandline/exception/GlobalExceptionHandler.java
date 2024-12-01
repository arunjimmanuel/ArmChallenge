package com.arm.challenge.commandline.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getLocalizedMessage()));
	}

	@ExceptionHandler(NoJsonFilesFoundException.class)
	public ResponseEntity<Object> handleJsonFilesNotFound(NoJsonFilesFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getLocalizedMessage()));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGenericException(Exception ex) {
	    System.out.println("Caught Generic Exception: " + ex.getClass().getName() + " - " + ex.getMessage());
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unexpected error occurred"));
	}
}

class ErrorResponse {
	private int status;
	private String message;

	public ErrorResponse(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}
}
