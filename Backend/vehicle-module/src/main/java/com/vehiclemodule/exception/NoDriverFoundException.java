package com.vehiclemodule.exception;

public class NoDriverFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public NoDriverFoundException(String message) {
		super(message);
	}
}
