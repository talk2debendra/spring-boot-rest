package com.hsc.authenication.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "subscriber")
public class Subscriber extends BaseEntity{
	private static final long serialVersionUID = 123098L;
	
	
	@Column(name="subscriberId",unique=true)
	private String subscriberId;
	
	@Column(name="userName",unique=true)
	private String userName;
	private String password;
	
	
	@Column(name="authToken",length=1056)
	private String authToken;
	
	@Column(name="refreshToken",length=1056)
	private String refreshToken;
	
	private String tokenExpiry;

	
	
	//getters&setters
	
	public String getUserName() {
		return userName;
	}

	public String getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(String subscriberId) {		
		this.subscriberId = subscriberId;
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

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
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

	
}
