package com.planner.godsaeng.security.oauth.dto;

import java.util.Map;

import com.planner.godsaeng.entity.Provider;
import com.planner.godsaeng.entity.Role;
import com.planner.godsaeng.entity.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuth2Attributes {
	private String nameAttributeKey; // OAuth2 로그인 진행 시 키가 되는 필드 값, PK와 같은 의미
	private OAuth2UserInfo oauth2UserInfo; // 소셜 타입별 로그인 유저 정보(닉네임, 이메일, 프로필 사진 등등)

	@Builder
	public OAuth2Attributes(String nameAttributeKey, OAuth2UserInfo oauth2UserInfo) {
		this.nameAttributeKey = nameAttributeKey;
		this.oauth2UserInfo = oauth2UserInfo;
	}

	public static OAuth2Attributes of(Provider provider,
		String userNameAttributeName, Map<String, Object> attributes) {

//		if (provider == Provider.GOOGLE) {
//			return ofGoogle(userNameAttributeName, attributes);
//		}
		return ofKakao(userNameAttributeName, attributes);
	}

	private static OAuth2Attributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
		return OAuth2Attributes.builder()
			.nameAttributeKey(userNameAttributeName)
			.oauth2UserInfo(new KakaoOAuth2UserInfo(attributes))
			.build();
	}

//	public static OAuth2Attributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
//		return OAuth2Attributes.builder()
//			.nameAttributeKey(userNameAttributeName)
//			.oauth2UserInfo(new GoogleOAuth2UserInfo(attributes))
//			.build();
//	}

	public User toEntity(Provider provider, OAuth2UserInfo oauth2UserInfo) {
		return User.builder()
			.role(Role.USER)
			.build();
	}
}


