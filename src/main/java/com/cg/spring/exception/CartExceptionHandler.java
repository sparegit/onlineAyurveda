package com.cg.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.spring.model.CartErrorResponse;

@ControllerAdvice
public class CartExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<CartErrorResponse> handleException(CartNotFoundException exception) {
		CartErrorResponse error = new CartErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());// 404
		error.setMessage(exception.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<CartErrorResponse> handleException(Exception exception) {
		CartErrorResponse error = new CartErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());// 400
		error.setMessage(exception.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
