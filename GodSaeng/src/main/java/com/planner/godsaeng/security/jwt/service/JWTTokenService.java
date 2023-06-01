package com.planner.godsaeng.security.jwt.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.planner.godsaeng.repository.UserRepository;
import com.planner.godsaeng.security.jwt.JwtTokenProvider;
import com.planner.godsaeng.security.jwt.repository.JWTRefreshTokenRepository;
import com.planner.godsaeng.security.oauth.dto.TokenRefreshResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JWTTokenService {
	
	private final JwtTokenProvider jwtTokenProvider;
	private final JWTRefreshTokenRepository refreshTokenRepository;
	private final UserRepository userRepository;
	
	@Value("${jwt.expire-seconds.access-token}")
	int accessTokenExpireSeconds;
	
	@Value("${jwt.expire-seconds.refresh.token}")
	int refreshTokenExpireSeconds;
	
	@Transactional
	public TokenRefreshResponseDTO reFreshTokens(String token) {
		return null;
	}

}
