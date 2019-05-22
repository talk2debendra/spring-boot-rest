package com.hsc.authenication.service.impl;


import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.hsc.authenication.dto.JWTTokenDto;
import com.hsc.authenication.service.AuthTokenGeneratorServie;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@PropertySource("classpath:auth_token.properties")
public class JWTAuthTokenService implements AuthTokenGeneratorServie{

	@Autowired
	private Environment environment; 

	private Key generateKey() {
		
		byte[] keyBytes=environment.getProperty("auth.jwt.secret.key").getBytes();
		
		return new SecretKeySpec(keyBytes, 0,keyBytes.length,environment.getProperty("auth.jwt.secret.algo"));
	}
	
	
	public JWTTokenDto generateJWTToken(String username, String urlAbsolutePath){
		
		JWTTokenDto dto = new JWTTokenDto();
		
		String token= Jwts.builder()
				.setHeaderParam("typ", "jwt")
				.claim("name", Base64.getEncoder().encodeToString(username.getBytes()))
				.setSubject("accessAuth")
				.setIssuer(urlAbsolutePath)
				.setIssuedAt(new Date())
				.setExpiration(toDate(LocalDateTime.now().plusMinutes(Long.valueOf(environment.getProperty("auth.jwt.token.expiry")))))
				.claim("admin", "true")
				.claim("userType", "premium")
				.signWith(SignatureAlgorithm.HS512, generateKey())
				.compact();
		
		String tokenExpiry=Jwts.parser().setSigningKey(generateKey())
						.parseClaimsJws(token)
						.getBody()
						.getExpiration()
						.toString();
		
		
		dto.setToken(token);
		dto.setTokenExpiry(tokenExpiry);
		
		return dto;
	}

	public boolean isValidJWTToken(String jwtToken){
		boolean isValid = true;
		try {
			Jwts.parser().setSigningKey(generateKey()).parseClaimsJws(jwtToken);
		} catch (Exception e) {
			isValid = false;
		}
		return isValid;
	}

	
	
	private static Date toDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
}
