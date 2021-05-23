package com.cg.spring.exception;

public class MedicineNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MedicineNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public MedicineNotFoundException(String message) {
		super(message);
	}

	public MedicineNotFoundException(Throwable cause) {
		super(cause);
	}
}
