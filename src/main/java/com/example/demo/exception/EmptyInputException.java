package com.example.demo.exception;

public class EmptyInputException extends RuntimeException {

	private String errorCode;

	private Object[] args;

	public EmptyInputException(String message, String errorCode, Object[] args) {
		super(message);
		this.errorCode = errorCode;
		this.args = args;
	}

	public EmptyInputException(String message) {
		super(message);
	}

	public EmptyInputException() {
		super();
	}

	public String getErrorCode() {
		return errorCode;
	}

	public Object[] getArgs() {
		return args;
	}

}