package com.myntra.exception;

import org.springframework.http.HttpStatus;

public class MyntraException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7252602450875824238L;

	public  MyntraException(String message,HttpStatus status) {
		super(message);
	}
	

}
