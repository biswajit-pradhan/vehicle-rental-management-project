package com.bookingmodule.exception;

public class NoBookingsFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NoBookingsFoundException(String message) {
		super(message);
	}
}