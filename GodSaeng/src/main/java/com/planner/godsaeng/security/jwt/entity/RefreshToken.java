package com.planner.godsaeng.security.jwt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class RefreshToken {
	
	@Id
	private String userId;
	
	@Column(nullable = false, unique = true)
	private String refreshToken;
	
	@Builder
	public RefreshToken(String userId, String refreshToken) {
		this.userId = userId;
		this.refreshToken = refreshToken;
	}
	
	public void update(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
