package com.example.demoparkapi.exceptions;

public class CodigoUniqueVioleationException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public CodigoUniqueVioleationException (String msg) {
		super(msg);
		
	}

}
