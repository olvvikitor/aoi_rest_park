package com.example.demoparkapi.exceptions;

public class CpfUniqueVioleationException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CpfUniqueVioleationException(String msg) {
		super(msg);
	}

}
