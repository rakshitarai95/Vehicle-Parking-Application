package com.test.exception;

public class ParkingException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The status code. */
	private String statusCode;

	public ParkingException() {
		super();
	}

	public ParkingException(String message) {
		super(message);
	}

	public ParkingException(String code, String message) {
		super(message);
		this.statusCode = code;
	}

	public ParkingException(Throwable cause) {
		super(cause);
	}

	public ParkingException(String message, Throwable cause) {
		super(message, cause);
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

}
