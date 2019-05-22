package com.hsc.authenication.exception;

public class AuthTokenException extends RuntimeException{
	
	public AuthTokenException() {
		super();
	}
	
	public AuthTokenException(String exception) {
		super(exception);
	}
}
