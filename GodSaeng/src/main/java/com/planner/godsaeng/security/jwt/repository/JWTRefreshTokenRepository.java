package com.planner.godsaeng.security.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planner.godsaeng.security.jwt.entity.JWTRefreshToken;

public interface JWTRefreshTokenRepository extends JpaRepository<JWTRefreshToken, Long> {

}
