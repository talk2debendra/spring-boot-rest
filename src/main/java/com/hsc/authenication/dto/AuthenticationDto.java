package com.hsc.authenication.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class AuthenticationDto implements Serializable{

	private static final long serialVersionUID = 15223542L;
	

	@NotBlank(message="UserId Can't be blank")
	private String userId;
	@NotBlank(message="Password Can't be blank")
	private String password;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
