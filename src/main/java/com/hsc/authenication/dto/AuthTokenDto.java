package com.hsc.authenication.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class AuthTokenDto implements Serializable{

	private static final long serialVersionUID = 15223542L;
	
	
	@NotBlank(message="Token can't be empty")
	private String token;
	private String refreshToken;
	private String tokenExpiry;
	private String subscriberId;
	
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
	public String getSubscriberId() {
		return subscriberId;
	}
	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}
}
