package com.hsc.authenication.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hsc.authenication.dto.AuthTokenDto;
import com.hsc.authenication.dto.AuthenticationDto;
import com.hsc.authenication.service.AuthenticationServie;

@SuppressWarnings("rawtypes")
@RestController
public class AuthTokenController {
	
	@Autowired
	private AuthenticationServie authenticationServie;
	
	
	@PostMapping("/subscriber/token")
	public ResponseEntity getAuthToken(@Valid @RequestBody AuthenticationDto authenticationDto,HttpServletRequest request){
		return new ResponseEntity<>(authenticationServie.getAuthToken(authenticationDto, request),HttpStatus.OK);
	}
	
	
	
	@PostMapping("/subscriber/token/valid")
	public ResponseEntity getAuthToken(@Valid @RequestBody AuthTokenDto authTokenDto){
		return new ResponseEntity<>(authenticationServie.isValidToken(authTokenDto),HttpStatus.OK);
	}
}
