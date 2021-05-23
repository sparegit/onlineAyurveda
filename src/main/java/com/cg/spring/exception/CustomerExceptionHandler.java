package com.cg.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.spring.model.CustomerErrorResponse;

@ControllerAdvice
public class CustomerExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exception) {
		CustomerErrorResponse error = new CustomerErrorResponse();

		error.setStatus(HttpStatus.NOT_FOUND.value()); // 404
		error.setMessage("Not Found");
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(Exception exception) {
		CustomerErrorResponse error = new CustomerErrorResponse();

		error.setStatus(HttpStatus.BAD_REQUEST.value()); // 400
		error.setMessage("Bad Request");
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
