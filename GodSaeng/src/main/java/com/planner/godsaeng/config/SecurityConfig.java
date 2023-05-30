package com.planner.godsaeng.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
//	@Bean
//	public SecurityFilterChain httpSecurity(HttpSecurity http)throws Exception{
//		return http
//				.csrf().disable()
//				.cors()
//				.and()
//				.authorizeHttpRequests()
//				.antMatchers("/godsaeng").permitAll()
//				.antMatchers("/godsaengall").permitAll()
//				.anyRequest().authenticated()
//				.and()
//				.oauth2Login()
//				.successHandler(oAuth2LoginFailureHandler)
//				.failureHandler(oAuth2LoginSuccessHandler)
//				.userInfoEndpoint().userService(customOAuth2UserService);
//				.build();
//	}

}
