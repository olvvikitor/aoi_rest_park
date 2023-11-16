package com.example.demoparkapi.exceptions;

public class UserNameUniqueException extends RuntimeException {


	private static final long serialVersionUID = 1L;

	public UserNameUniqueException(String msg) {
		super(msg);
	}
}
