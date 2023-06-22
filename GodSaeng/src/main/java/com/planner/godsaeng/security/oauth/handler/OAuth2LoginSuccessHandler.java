package com.planner.godsaeng.security.oauth.handler;

import static com.planner.godsaeng.security.oauth.HttpCookieOAuth2AuthorizationRequestRepository.*;


import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.planner.godsaeng.entity.Role;
import com.planner.godsaeng.security.jwt.JwtTokenProvider;
import com.planner.godsaeng.security.oauth.CustomOAuth2User;
import com.planner.godsaeng.security.oauth.HttpCookieOAuth2AuthorizationRequestRepository;
import com.planner.godsaeng.util.CookieUtils;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private final JwtTokenProvider tokenProvider;
	private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;
	
	@Value("${jwt.expire-seconds.access-token}")
	long accessTokenExpireSeconds;
	
	@Value("${jwt.expire-seconds.refresh-token}")
	long refreshTokenExpireSeconds;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, 
		HttpServletResponse response,Authentication authentication)throws IOException{
		CustomOAuth2User oAuth2User = (CustomOAuth2User)authentication.getPrincipal();
		if(oAuth2User.getRole() == Role.TEMP) {
			String accessToken = tokenProvider.createAccessToken(oAuth2User.getUid(), oAuth2User.getRole().name(), oAuth2User.getProfileimage());
			String refreshToken = tokenProvider.createRefreshToken();
			
			setAccessTokenInCookie(response,accessToken);
			setRefreshTokenInCookie(response, refreshToken);
			
			tokenProvider.updateRefreshToken(oAuth2User.getUid(), refreshToken);
			response.sendRedirect(determineTargetUrl(request, response,authentication));
		}else {
			loginSuccess(response,oAuth2User);
			response.sendRedirect(determineTargetUrl(request, response, authentication));
			
		}
			
		
	}
	private void setAccessTokenInCookie(HttpServletResponse response, String accessToken) {
		ResponseCookie token = ResponseCookie.from("accessTokenCookie", accessToken)
			.path(getDefaultTargetUrl())
			.sameSite("None")
			.secure(true)
			.maxAge(accessTokenExpireSeconds)
			.build();

		response.addHeader("Set-Cookie", token.toString());
	}

	private void setRefreshTokenInCookie(HttpServletResponse response, String refreshToken) {
		ResponseCookie token = ResponseCookie.from("refreshTokenCookie", refreshToken)
			.path(getDefaultTargetUrl())
			.sameSite("None")
			.secure(true)
			.maxAge(refreshTokenExpireSeconds)
			.build();

		response.addHeader("Set-Cookie", token.toString());
	}
	
	private void loginSuccess(HttpServletResponse response, CustomOAuth2User oAuth2User) throws IOException {

		String accessToken = tokenProvider.createAccessToken(oAuth2User.getUid(), oAuth2User.getRole().name(), oAuth2User.getProfileimage());
		String refreshToken = tokenProvider.createRefreshToken();

		setAccessTokenInCookie(response, accessToken);
		setRefreshTokenInCookie(response, refreshToken);

		tokenProvider.updateRefreshToken(oAuth2User.getUid(), refreshToken);
	}
  
	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
			Optional<String> redirectUri = CookieUtils.getCookie(request,REDIRECT_URI_PARAM_COOKIE_NAME)
				.map(Cookie::getValue);
			String targetUrl;
			
			if (authentication.getAuthorities().toString().equals("[ROLE_TEMP]")) {
				targetUrl = redirectUri.orElse(getCustomDefaultTargetUrl());
			} else {
				targetUrl = redirectUri.orElse(getCustomDefaultTargetUrl2());
//					.substring(0, redirectUri.orElse(getDefaultTargetUrl()));
			}
			return targetUrl;
		}
	
	 protected String getCustomDefaultTargetUrl() {
	       return "https://frontend-inky-pi.vercel.app/signUp";
	   }
	 
	 protected String getCustomDefaultTargetUrl2() {
	       return "https://frontend-inky-pi.vercel.app/planner";
	   }
		
}
