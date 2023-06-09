package com.planner.godsaeng.security.jwt;

import com.planner.godsaeng.security.jwt.exception.JWTInvalidException;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class JwtAuthentication {

	public final String accessToken;
	public final String userId;
	public final String role;
	
	public JwtAuthentication(String accessToken, String userId, String role) {
		if(accessToken.isEmpty() || accessToken.isBlank()) {
			throw new JWTInvalidException();
		}
//		if (userId <= 0 || userId == null) {
		if (userId == null) {
			throw new JWTInvalidException();
		}
		this.accessToken = accessToken;
		this.userId = userId;
		this.role = role;
	}
}
