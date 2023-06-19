package com.planner.godsaeng.security.jwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planner.godsaeng.security.jwt.entity.RefreshToken;

public interface JWTRefreshTokenRepository extends JpaRepository<RefreshToken, String> {

	Optional<RefreshToken> findByUserId(String userId);
	
	Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
