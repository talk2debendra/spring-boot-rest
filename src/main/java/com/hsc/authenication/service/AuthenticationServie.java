package com.hsc.authenication.service;

import javax.servlet.http.HttpServletRequest;

import com.hsc.authenication.dto.AuthTokenDto;
import com.hsc.authenication.dto.AuthenticationDto;
import com.hsc.authenication.dto.BaseResponseDto;

public interface AuthenticationServie {

	
	/**
	 * Checks whether the subscriber is valid or not based on the provided userId & password
	 * @param authenticationDto 
	 * @return true/false true: Valid subscriber, false:Invalid subscriber
	 * */
	public boolean isValidSubscriber(AuthenticationDto authenticationDto );

	
	
	/**
	 * Checks whether the subscriber is valid or not based on the provided userId & password
	 * @param authenticationDto 
	 * @return true/false true: Valid subscriber, false:Invalid subscriber
	 * */
	public default boolean authenticate(AuthenticationDto authenticationDto) {
    	return isValidSubscriber(authenticationDto);
    }
    
    
	/**
	 * Checks whether the subscriber is valid or not based on the provided userId & password
	 * If valid, generate the token iff not generated before or expired
	 * If the user has generated a token before, return the same token
	 * @param authenticationDto 
	 * @return {@link AuthenticationDto} 
	 * */
    public AuthTokenDto getAuthToken(AuthenticationDto authenticationDto,HttpServletRequest request);
    
    
    
    public boolean isValidToken(AuthTokenDto authTokenDto);
}
