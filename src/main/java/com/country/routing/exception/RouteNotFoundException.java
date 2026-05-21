package com.country.routing.exception;

/**
 * Thrown when no land route exists
 */

public class RouteNotFoundException extends RuntimeException {
	public RouteNotFoundException(String message) {
		super(message);
	}
}
