package com.planner.godsaeng.security.oauth;

import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.planner.godsaeng.entity.Provider;
import com.planner.godsaeng.entity.User;
import com.planner.godsaeng.repository.UserRepository;
import com.planner.godsaeng.security.oauth.dto.OAuth2Attributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User>{

	
	private final UserRepository userRepository;
	private static final String KAKAO = "kakao";
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
		
		OAuth2UserService<OAuth2UserRequest, OAuth2User>delegate = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = delegate.loadUser(userRequest);
		
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		Provider provider = getProvider(registrationId);
		String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
				.getUserInfoEndpoint().getUserNameAttributeName();
		Map<String,Object>attributes = oAuth2User.getAttributes();
		
		OAuth2Attributes extractAttributes = OAuth2Attributes.of(provider, userNameAttributeName, attributes);
		
		User user = getUser(provider,extractAttributes);
		
		return new CustomOAuth2User(
				Collections.singleton(new SimpleGrantedAuthority(user.getRole().getKey())),
				attributes,
				extractAttributes.getNameAttributeKey(),
				user.getUid(),
				user.getRole(),
				user.getProfileimage());
	}
	
	private Provider getProvider(String registrationId) {
			return Provider.KAKAO;
		}
	private User getUser(Provider provider, OAuth2Attributes attributes) {
		User findUser = userRepository.findByProviderAndUid(provider,
			attributes.getOauth2UserInfo().getEmail()).orElse(null);

		if (findUser == null) {
			return saveUser(provider, attributes);
		}
		return findUser;
	}

	private User saveUser(Provider provider, OAuth2Attributes attributes) {
		User user = attributes.toEntity(provider, attributes.getOauth2UserInfo());
		return userRepository.save(user);
	}

}

