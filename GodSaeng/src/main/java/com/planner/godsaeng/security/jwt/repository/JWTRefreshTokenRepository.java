package com.planner.godsaeng.security.jwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planner.godsaeng.security.jwt.entity.RefreshToken;

public interface JWTRefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

	Optional<RefreshToken> findByUserId(Long userId);
	
	Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
