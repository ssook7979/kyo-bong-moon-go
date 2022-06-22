package com.kyobong.store.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	
	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler({ ConstraintViolationException.class})
	public Map<String, List<String>> handleConstraintViolationException(ConstraintViolationException e) {
		Map<String, List<String>> errors = new HashMap<>();
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		for (ConstraintViolation<?> violation: violations) {
			String field = getFieldFromPath(violation.getPropertyPath());
			String message = violation.getMessage();
			if (!errors.containsKey(field)) {
				errors.put(field, new ArrayList<>());
			}
			errors.get(field).add(message);
		}
		return errors;
	}
	
	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public Map<String, List<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		Map<String, List<String>> errors = new HashMap<>();
		for (FieldError error: e.getFieldErrors()) {
			String field = error.getField();
			String message = error.getDefaultMessage();
			if (!errors.containsKey(field)) {
				errors.put(field, new ArrayList<>());
			}
			errors.get(field).add(message);
		}
		
		return errors;
	}
	
	private String getFieldFromPath(Path fieldPath) {
		
	    PathImpl pathImpl = (PathImpl) fieldPath;
	    return pathImpl.getLeafNode().toString();
	}

}
