package com.kyobong.store.controller;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler({ NoSuchElementException.class })
	public String handleNoSuchElementException(NoSuchElementException e) {
		return e.getMessage();
		
	}
	
	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler({ HttpMessageNotReadableException.class })
	public String handleIllegalArgumentException(HttpMessageNotReadableException e) {
		return e.getCause().getMessage();
	}

}
