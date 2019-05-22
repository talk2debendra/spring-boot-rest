package com.hsc.authenication.dto;

import java.io.Serializable;

public class BaseResponseDto implements Serializable{

	private static final long serialVersionUID = 13456;
	
	private String status;
	private String message;
	private String statusCode;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
}
