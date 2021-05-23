package com.cg.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.spring.model.OrderErrorResponse;

@ControllerAdvice
public class OrderExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<OrderErrorResponse> handleException(OrderNotFoundException exception) {
		OrderErrorResponse error = new OrderErrorResponse();

		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exception.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler
	public ResponseEntity<OrderErrorResponse> handleException(Exception exception) {
		OrderErrorResponse error = new OrderErrorResponse();

		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exception.getMessage());
		error.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
