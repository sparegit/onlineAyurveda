package com.cg.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.spring.model.CategoryErrorRespose;

@ControllerAdvice
public class CategoryExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<CategoryErrorRespose> handleException(CategoryNotFoundException exception) {
		CategoryErrorRespose error = new CategoryErrorRespose();
		error.setStatus(HttpStatus.NOT_FOUND.value());// 404
		error.setMessage("Not Found");
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<CategoryErrorRespose> handleException(Exception exception) {
		CategoryErrorRespose error = new CategoryErrorRespose();
		error.setStatus(HttpStatus.BAD_REQUEST.value());// 400
		error.setMessage(exception.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
