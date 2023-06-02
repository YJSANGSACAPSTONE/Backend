package com.planner.godsaeng.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleHierarchyVoter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.planner.godsaeng.security.oauth.handler.OAuth2LoginFailureHandler;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain httpSecurity(HttpSecurity http)throws Exception{
		return http
				.csrf().disable()
				.cors()
				
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				
				.and()
				.authorizeHttpRequests()
				.antMatchers("/").permitAll()
				.antMatchers
				("/challenge/**","/plan/**","/board/**","/comments/**"
						,"/kakaopay/**","/post/**","/user/**","/usage/**")
				.hasRole("USER")
				
				.antMatchers
				("/challenge/addchallenge","/challenge/update",
						"/challenge/delete","/challenge/adminverify")
				.hasRole("CHALLENGEMANAGER")
				
				.antMatchers("/library/**").hasRole("LIBRARYMANAGER")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()
				.and()
				.build();
//				.and()
//				.oauth2Login()
//				.authorizationEndpoint().baseUri("/oauth2/authorize")
//				.authorizationRequestRepository(cookieOAuth2AuthorizationRequestRepository())
//				.and()
//				.redirectionEndpoint().baseUri("/login/oauth2/code/**")
//				.and()
//				.userInfoEndpoint().userService(customOAuth2UserService)
//				.and()
//				.successHandler(oAuth2AuthenticationSuccessHandler)
//				.failureHandler(oAuth2AuthenticationFailureHandler)
//				.and()
//				.sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//				.and()
//				.exceptionHandling()
//				.authenticationEntryPoint(jwtAuthenticationEntryPoint)
//				.and()
//				.addFilterBefore(jwtAuthenticationFilter, OAuth2AuthorizationRequestRedirectFilter.class)
//				.addFilterBefore(exceptionHandlerFilter, JwtAuthenticationFilter.class)
				
	}
	
	@Bean
	public RoleHierarchyImpl roleHierarchy() {
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        return roleHierarchy;

	}
	@Bean
	public AccessDecisionVoter<? extends Object> roleVoter() {
        RoleHierarchyVoter roleHierarchyVoter = new RoleHierarchyVoter(roleHierarchy());
        return roleHierarchyVoter;
    }
	 
	private List<AccessDecisionVoter<?>> getAccessDecisionVoters() {
	        List<AccessDecisionVoter<? extends Object>> accessDecisionVoters = new ArrayList<>();
	        accessDecisionVoters.add(roleVoter()); // 계층 voter
	        return accessDecisionVoters;
	    }
	private AccessDecisionManager affirmativeBased() {
        AffirmativeBased affirmativeBased = new AffirmativeBased(getAccessDecisionVoters());
        return affirmativeBased;
    }
	

}
