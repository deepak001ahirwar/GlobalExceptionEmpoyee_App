package com.example.demo.exception;

public class ResourceNotFoundException extends RuntimeException {

	private String errorCode;

	private Object[] args;

	public ResourceNotFoundException(String message, String errorCode, Object[] args) {
		super(message);
		this.errorCode = errorCode;
		this.args = args;
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException() {
		super();
	}

	public String getErrorCode() {
		return errorCode;
	}

	public Object[] getArgs() {
		return args;
	}

	
	
}
