package com.product.exception;

public class ProductNotFoundException extends Exception {
	private String message;

	public ProductNotFoundException() {
	}

	public ProductNotFoundException(String msg) {
		message = msg;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
