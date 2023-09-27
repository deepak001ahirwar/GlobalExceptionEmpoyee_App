package com.example.demo.exception;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@Autowired
	private MessageSource messagesource;

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponce> handleResourceNotFoundException(ResourceNotFoundException ex, Locale local) {
//		String message = messagesource.getMessage(ex.getErrorCode(), ex.getArgs(), local);
		ErrorResponce responce = new ErrorResponce(HttpStatus.NOT_FOUND.value(),ex.getMessage(), ex.getErrorCode(), null);
		return new ResponseEntity<ErrorResponce>(responce, HttpStatus.NOT_FOUND);
	}

	
	
	@ExceptionHandler(EmptyInputException.class)
	public ResponseEntity<ErrorResponce> handleEmptyInputException(EmptyInputException ex) {

//		String message = messagesource.getMessage(ex.getErrorCode(), ex.getArgs(), null);
		ErrorResponce responce = new ErrorResponce(HttpStatus.BAD_REQUEST.value(),ex.getMessage(), ex.getErrorCode(),  null);
		return new ResponseEntity<ErrorResponce>(responce, HttpStatus.BAD_REQUEST);

	}
	
	
	
	
	
	
}
