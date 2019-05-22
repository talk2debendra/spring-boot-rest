package com.hsc.authenication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.CONFLICT)
public class UniqueConstraintsViolationException extends RuntimeException{
	
	public UniqueConstraintsViolationException() {
		super();
	}
	
	public UniqueConstraintsViolationException(String exception) {
		super(exception);
	}
}
