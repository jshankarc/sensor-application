package com.assignment.sensor.reporter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * API Error handler if resource or page not found
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public RecordNotFoundException(String message) {
		super(message);
	}
	
	public RecordNotFoundException(String message, Throwable t) {
		super(message, t);
	}
}
