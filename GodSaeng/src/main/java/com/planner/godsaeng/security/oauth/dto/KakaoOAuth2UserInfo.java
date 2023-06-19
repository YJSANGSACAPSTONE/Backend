package com.planner.godsaeng.security.oauth.dto;

import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {

	public KakaoOAuth2UserInfo(Map<String, Object>attributes) {
		super(attributes);
	}
	
	@Override
	public String getEmail() {
		return String.valueOf(((Map<String, Object>) attributes.get("kakao_account")).get("email"));
	}
	
	@Override
	public String getProfileImage() {
		return String.valueOf(((Map<String, Object>) ((Map<String, Object>) attributes.get("kakao_account")).get("profile")).get("profile_image_url"));
	}

}