package com.country.routing.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Handles all exceptions globally
 */

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(RouteNotFoundException.class)
	public ResponseEntity<String> handle(RouteNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
}
