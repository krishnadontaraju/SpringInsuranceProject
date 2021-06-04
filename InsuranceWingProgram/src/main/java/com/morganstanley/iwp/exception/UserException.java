package com.morganstanley.iwp.exception;

public class UserException extends RuntimeException{

	private int code;

	public UserException(int code , String message) {
		super(message);
		this.code = code;
	}
	
}
