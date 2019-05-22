package com.hsc.authenication.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class SubscriberDto implements Serializable{

	private static final long serialVersionUID = 15223542L;
	
	private Long id;
	
	@NotBlank(message="UserName Can't be blank")
	private String userName;
	@NotBlank(message="Password Can't be blank")
	private String password;
	
	private boolean isActive;
	
	private AuthTokenDto authToken;
	
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public AuthTokenDto getAuthToken() {
		return authToken;
	}
	public void setAuthToken(AuthTokenDto authToken) {
		this.authToken = authToken;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
}
