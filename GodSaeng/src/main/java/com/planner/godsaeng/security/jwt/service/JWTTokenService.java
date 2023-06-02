package com.planner.godsaeng.security.jwt.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.planner.godsaeng.entity.User;
import com.planner.godsaeng.repository.UserRepository;
import com.planner.godsaeng.security.jwt.JwtAuthentication;
import com.planner.godsaeng.security.jwt.JwtAuthenticationToken;
import com.planner.godsaeng.security.jwt.JwtTokenProvider;
import com.planner.godsaeng.security.jwt.dto.TokenReIssueResponseDto;
import com.planner.godsaeng.security.jwt.entity.RefreshToken;
import com.planner.godsaeng.security.jwt.exception.RefreshTokenCannotFoundException;
import com.planner.godsaeng.security.jwt.repository.JWTRefreshTokenRepository;
import com.planner.godsaeng.util.CookieUtils;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JWTTokenService {

	private final JwtTokenProvider jwtTokenProvider;
	private final JWTRefreshTokenRepository refreshTokenRepository;
	private final UserRepository userRepository;

	@Value("${jwt.expire-seconds.access-token}")
	int accessTokenExpireSeconds;
	@Value("${jwt.expire-seconds.refresh-token}")
	int refreshTokenExpireSeconds;

	@Transactional
	public TokenReIssueResponseDto reIssueTokens(String token) {

		jwtTokenProvider.validateToken(token);

		RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(token)
			.orElseThrow(() -> new RefreshTokenCannotFoundException());

		User user = userRepository.findByUid(refreshToken.getUserId())
			.orElseThrow(() -> new UserNotFoundException());

		String reIssuedAccessToken = reIssueAccessToken(user.getUid(), user.getRole().name());
		String reIssuedRefreshToken = reIssueRefreshToken(user.getUid());

		String accessTokenCookie = createAccessTokenCookie(reIssuedAccessToken);
		String refreshTokenCookie = createRefreshTokenCookie(reIssuedRefreshToken);

		return new TokenReIssueResponseDto(accessTokenCookie, refreshTokenCookie);

	}

	public String reIssueAccessToken(String userId, String role) {
		return jwtTokenProvider.createAccessToken(userId, role);
	}

	@Transactional
	public String reIssueRefreshToken(String userId) {
		String token = jwtTokenProvider.createRefreshToken();
		RefreshToken reIssuedRefreshToken = new RefreshToken(userId, token);

		return refreshTokenRepository.save(reIssuedRefreshToken).getRefreshToken();
	}

	private String createAccessTokenCookie(String accessToken) {
		return CookieUtils.addCookie("accessTokenCookie", accessToken, accessTokenExpireSeconds);
	}

	private String createRefreshTokenCookie(String refreshToken) {
		return CookieUtils.addCookie("refreshTokenCookie", refreshToken, refreshTokenExpireSeconds);
	}

	public Optional<String> getAccessToken(HttpServletRequest request) {
		String accessToken = jwtTokenProvider.extractAccessToken(request).orElse(null);
		if (accessToken != null) {
			jwtTokenProvider.validateToken(accessToken);
		}
		return Optional.ofNullable(accessToken);
	}

	public JwtAuthenticationToken getAuthentication(String accessToken) {

		Claims claims = jwtTokenProvider.getClaims(accessToken);

		String userId = claims.get("userId", String.class);
		String role = claims.get("role", String.class);

		JwtAuthentication principal = new JwtAuthentication(accessToken, userId, role);
		List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(role));

		return new JwtAuthenticationToken(principal, null, authorities);
	}
}
