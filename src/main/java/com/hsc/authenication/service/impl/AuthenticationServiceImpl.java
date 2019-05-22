package com.hsc.authenication.service.impl;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.hsc.authenication.dto.AuthTokenDto;
import com.hsc.authenication.dto.AuthenticationDto;
import com.hsc.authenication.dto.JWTTokenDto;
import com.hsc.authenication.entity.Subscriber;
import com.hsc.authenication.exception.AuthenticationException;
import com.hsc.authenication.exception.ResourceNotFoundException;
import com.hsc.authenication.service.AuthenticationServie;
import com.hsc.authenication.service.SubscriberServie;

@Service
public class AuthenticationServiceImpl implements AuthenticationServie{

	@Autowired
	private Environment environment;
	
	@Autowired
	private SubscriberServie subscriberServie; 
	
	@Autowired
	private JWTAuthTokenService jwtAuthTokenService;
	
	@Override
	public boolean isValidSubscriber(AuthenticationDto authenticationDto) {
		Subscriber subscriber=subscriberServie.getSubscriberByUserName(authenticationDto.getUserId());
		if(subscriber!=null){
			//check for password
			if(!(subscriber.getPassword().equals(authenticationDto.getPassword())))
				throw new AuthenticationException();
		}else{
			throw new AuthenticationException();
		}
		return true;
	}

	@Override
	public AuthTokenDto getAuthToken(AuthenticationDto authenticationDto,HttpServletRequest request) {
		
		isValidSubscriber(authenticationDto);
		
		AuthTokenDto authTokenDto = new AuthTokenDto();
		
		Subscriber  subscriber = subscriberServie.getSubscriberByUserName(authenticationDto.getUserId());
		if(subscriber!=null){
			JWTTokenDto jwtToken =StringUtils.isEmpty(subscriber.getAuthToken())?
					jwtAuthTokenService.generateJWTToken(authenticationDto.getUserId(),request.getRequestURL().toString())
					:null;
					
			if(jwtToken!=null){
				subscriber.setAuthToken(jwtToken.getToken());
				subscriber.setTokenExpiry(jwtToken.getTokenExpiry());
				subscriberServie.update(subscriber);	
			}
			
			authTokenDto.setToken(subscriber.getAuthToken());
			authTokenDto.setTokenExpiry(subscriber.getTokenExpiry());
			authTokenDto.setSubscriberId(subscriber.getSubscriberId());
			
		}else{
			throw new ResourceNotFoundException(environment.getProperty("error.subscriber.not.exists"));
		}
		return authTokenDto;
	}

	@Override
	public boolean isValidToken(AuthTokenDto authTokenDto) {
		
		Subscriber subscriber =subscriberServie.getSubscriberByAuthToken(authTokenDto.getToken());
		if(ObjectUtils.isEmpty(subscriber)){
			return false;
		}
		return jwtAuthTokenService.isValidJWTToken(authTokenDto.getToken());
	}
}
