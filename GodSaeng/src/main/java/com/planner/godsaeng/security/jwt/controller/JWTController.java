package com.planner.godsaeng.security.jwt.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.planner.godsaeng.security.jwt.service.JWTTokenService;
import com.planner.godsaeng.security.oauth.dto.TokenRefreshResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/token")
public class JWTController {
	
	private final JWTTokenService jwtTokenService;
	
	@PostMapping(value = "", headers = "Authorization-refresh")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Void>reFreshTokens(
			@RequestHeader("Authorization-refresh")String refreshToken){
		
		TokenRefreshResponseDTO tokenResponse = jwtTokenService.reFreshTokens(refreshToken);
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.add("Set-Cookie", tokenResponse.getAccessToken());
		headers.add("Set-Cookie", tokenResponse.getRefreshToken());
		
		return ResponseEntity.ok()
				.headers(headers)
				.build();
		
	}
	

}
