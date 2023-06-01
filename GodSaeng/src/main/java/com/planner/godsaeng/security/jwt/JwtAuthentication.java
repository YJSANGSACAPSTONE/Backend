package com.planner.godsaeng.security.jwt;

import com.planner.godsaeng.security.jwt.exception.JWTInvalidException;

import lombok.ToString;

@ToString
public class JwtAuthentication {

	public final String accessToken;
	public final Long userId;
	public final String role;
	
	public JwtAuthentication(String accessToken, Long userId, String role) {
		if(accessToken.isEmpty() || accessToken.isBlank()) {
			throw new JWTInvalidException();
		}
		if (userId <= 0 || userId == null) {
			throw new JWTInvalidException();
		}
		if (userId <= 0 || userId == null) {
			throw new JWTInvalidException();
		}
		this.accessToken = accessToken;
		this.userId = userId;
		this.role = role;
	}
}
