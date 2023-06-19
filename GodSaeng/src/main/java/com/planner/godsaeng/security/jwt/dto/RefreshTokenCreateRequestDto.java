package com.planner.godsaeng.security.jwt.dto;

import com.planner.godsaeng.security.jwt.entity.RefreshToken;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RefreshTokenCreateRequestDto {
	private String userId;
	private String refreshToken;
	
	@Builder
	public RefreshTokenCreateRequestDto(String userId, String refreshToken) {
		this.userId = userId;
		this.refreshToken = refreshToken;
	}
	
	public RefreshToken toEntity() {
		return RefreshToken.builder()
				.userId(userId)
				.refreshToken(refreshToken)
				.build();
	}
}
