package com.planner.godsaeng.security.oauth.dto;

import java.util.Map;

public abstract class OAuth2UserInfo {

	protected Map<String, Object> attributes;

	public OAuth2UserInfo(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public abstract String getEmail();
	
	public abstract String getProfileImage();

}