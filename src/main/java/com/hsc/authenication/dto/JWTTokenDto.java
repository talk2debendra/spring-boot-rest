package com.hsc.authenication.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class JWTTokenDto implements Serializable{

	private static final long serialVersionUID = 15223542L;
	
	
	private String token;
	private String refreshToken;
	private String tokenExpiry;
	private String subject;
	private String userType;
	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getTokenExpiry() {
		return tokenExpiry;
	}
	public void setTokenExpiry(String tokenExpiry) {
		this.tokenExpiry = tokenExpiry;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
}
