package com.cg.spring.model;

public class MedicineErrorResponse {
	// Fields
	private int status;
	private String message;
	private long timeStamp;

	// Constructors
	public MedicineErrorResponse() {
	}

	public MedicineErrorResponse(int status, String message, long timeStamp) {
		super();
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
	}

	// Getters and Setters
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	// To String
	@Override
	public String toString() {
		return "MedicineErrorResponse [status=" + status + ", message=" + message + ", timeStamp=" + timeStamp + "]";
	}

}
