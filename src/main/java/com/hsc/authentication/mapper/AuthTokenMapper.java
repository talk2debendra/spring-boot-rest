package com.hsc.authentication.mapper;

import com.hsc.authenication.dto.AuthTokenDto;

public class AuthTokenMapper {

	public static AuthTokenDto toAuthToken(String authToken,String refreshToken,String tokenExpiry,String subscriberId){
		
		AuthTokenDto authTokenDto = new AuthTokenDto();
		authTokenDto.setToken(authToken);
		authTokenDto.setRefreshToken(refreshToken);
		authTokenDto.setTokenExpiry(tokenExpiry);
		authTokenDto.setSubscriberId(subscriberId);
		return authTokenDto;
	}
}
