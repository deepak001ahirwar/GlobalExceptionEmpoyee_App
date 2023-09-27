package com.example.demo.exception;

public class ErrorResponce {

	private Integer status;
	private String message;
	private String errorCode;
	private String[] errorMessages;

	public ErrorResponce(Integer status, String message, String errorCode, String[] errorMessages) {
		this.status = status;
		this.message = message;
		this.errorCode = errorCode;
		this.errorMessages = errorMessages;
	}

	public ErrorResponce() {
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String[] getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(String[] errorMessages) {
		this.errorMessages = errorMessages;
	}

	
	
	
	
}
