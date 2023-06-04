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
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.web.SecurityFilterChain;

import com.planner.godsaeng.security.jwt.filter.ExceptionHandlerFilter;
import com.planner.godsaeng.security.jwt.filter.JwtAuthenticationFilter;
import com.planner.godsaeng.security.oauth.CustomOAuth2UserService;
import com.planner.godsaeng.security.oauth.handler.OAuth2LoginFailureHandler;
import com.planner.godsaeng.security.oauth.handler.OAuth2LoginSuccessHandler;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final CustomOAuth2UserService customOAuth2UserService;
	private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
	private final OAuth2LoginFailureHandler oAuth2LoginFailureHandler;
	private final ExceptionHandlerFilter exceptionHandlerFilter;
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
	    return http
	            .csrf().disable()
	            .cors()
	            
	            .and()
	            .authorizeHttpRequests()
	            .antMatchers("/").permitAll()
	            .antMatchers(
	                    "/challenge/**","/plan/**","/board/**","/comments/**",
	                    "/kakaopay/**","/post/**","/user/**","/usage/**"
	            )
	            .hasRole("USER")
	            
	            .antMatchers(
	                    "/challenge/addchallenge","/challenge/update",
	                    "/challenge/delete","/challenge/adminverify"
	            )
	            .hasRole("CHALLENGEMANAGER")
	            
	            .antMatchers("/library/**").hasRole("LIBRARYMANAGER")
	            .antMatchers("/admin/**").hasRole("ADMIN")
	            .anyRequest().authenticated()
	            
	            .and()
	            .oauth2Login()
				.authorizationEndpoint().baseUri("/login")
				//.authorizationRequestRepository(HttpCookieOAuth2AuthorizationRequestRepository())
				.and()
				.redirectionEndpoint().baseUri("/login/oauth2/code/**")
				.and()
				.userInfoEndpoint().userService(customOAuth2UserService)
				.and()
				.successHandler(oAuth2LoginSuccessHandler)
				.failureHandler(oAuth2LoginFailureHandler)
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.exceptionHandling()
				//.authenticationEntryPoint(jwtAuthenticationEntryPoint)
				.and()
				.addFilterBefore(jwtAuthenticationFilter, OAuth2AuthorizationRequestRedirectFilter.class)
				.addFilterBefore(exceptionHandlerFilter, JwtAuthenticationFilter.class)
				
	            .build();

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
