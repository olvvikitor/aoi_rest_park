package com.example.demoparkapi.exceptions;

public class PasswordInvalidException extends RuntimeException {
	

	private static final long serialVersionUID = 1L;

	public PasswordInvalidException(String msg) {
		super (msg);
	}

}
