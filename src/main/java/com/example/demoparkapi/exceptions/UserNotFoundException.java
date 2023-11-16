package com.example.demoparkapi.exceptions;

public class UserNotFoundException extends RuntimeException {


	
	public UserNotFoundException (String msg) {
		super(msg);
	}

}
