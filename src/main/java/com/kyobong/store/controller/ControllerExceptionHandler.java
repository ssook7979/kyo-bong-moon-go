package com.kyobong.store.controller;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler({ NoSuchElementException.class })
	public String handleNoSuchElementException(NoSuchElementException e) {
		return e.getMessage();
		
	}
	
	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler({ IllegalArgumentException.class })
	public String handleIllegalArgumentException(IllegalArgumentException e) {
		return e.getMessage();
	}

}
