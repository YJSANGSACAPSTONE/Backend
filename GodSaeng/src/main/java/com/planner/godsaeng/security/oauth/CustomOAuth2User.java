package com.planner.godsaeng.security.oauth;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import com.planner.godsaeng.entity.Role;

public class CustomOAuth2User extends DefaultOAuth2User{
	
	private Role role;
	private String uid;
	
	public CustomOAuth2User(Collection<? extends GrantedAuthority>authorites, Map<String,Object>attributes,
			String nameAttributeKey, String uid, Role role) {
			super(authorites, attributes, nameAttributeKey);
			
			this.role = role;
			this.uid = uid;
	}
}
