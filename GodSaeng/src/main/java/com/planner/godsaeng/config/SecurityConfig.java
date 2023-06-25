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
import com.planner.godsaeng.security.jwt.filter.JwtAuthenticationEntryPoint;
import com.planner.godsaeng.security.jwt.filter.JwtAuthenticationFilter;
import com.planner.godsaeng.security.oauth.CustomOAuth2UserService;
import com.planner.godsaeng.security.oauth.HttpCookieOAuth2AuthorizationRequestRepository;
import com.planner.godsaeng.security.oauth.handler.OAuth2LoginFailureHandler;
import com.planner.godsaeng.security.oauth.handler.OAuth2LoginSuccessHandler;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {


	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final CustomOAuth2UserService customOAuth2UserService;
	private final OAuth2LoginSuccessHandler oAuth2AuthenticationSuccessHandler;
	private final OAuth2LoginFailureHandler oAuth2AuthenticationFailureHandler;
	private final ExceptionHandlerFilter exceptionHandlerFilter;

	@Bean
	public HttpCookieOAuth2AuthorizationRequestRepository cookieOAuth2AuthorizationRequestRepository() {
		return new HttpCookieOAuth2AuthorizationRequestRepository();
	}

//	private final AccessDecisionManager accessDecisionManager;
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
	    return http
	            .csrf().disable()
	            .cors()
	            .and()
	            .authorizeHttpRequests()
	            .antMatchers("/user/updateuser").hasAuthority("ADMIN")
//	            .antMatchers("/kakaopay/**").hasAuthority("TEMP")
	            .antMatchers("/**").permitAll()
//	            .antMatchers("/","/challenge/zepverify").permitAll()
//	            .antMatchers(
//	                    "/plan/**","/board/**","/comments/**",
//	                    "/kakaopay/**","/post/**","/user/**","/usage/**"
//	            )
//	            .hasRole("USER")
//	            .antMatchers(
//	                    "/challenge/addchallenge","/challenge/update",
//	                    "/challenge/delete","/challenge/adminverify"
//	            )
//	            .hasRole("CHALLENGEMANAGER")
//	            .antMatchers("/library/**").hasRole("LIBRARYMANAGER")
//	            .antMatchers("/admin/**").hasRole("ADMIN")
	            .anyRequest().authenticated()
				.and()
//				.accessDecisionManager(affirmativeBased())
				.oauth2Login()
				.authorizationEndpoint().baseUri("/oauth2/authorize")
				.authorizationRequestRepository(cookieOAuth2AuthorizationRequestRepository())
				.and()
				.redirectionEndpoint().baseUri("/login/oauth2/code/**")
				.and()
				.userInfoEndpoint().userService(customOAuth2UserService)
				.and()
				.successHandler(oAuth2AuthenticationSuccessHandler)
				.failureHandler(oAuth2AuthenticationFailureHandler)
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.exceptionHandling()
				.authenticationEntryPoint(jwtAuthenticationEntryPoint)
				.and()
				.addFilterBefore(jwtAuthenticationFilter, OAuth2AuthorizationRequestRedirectFilter.class)
				.addFilterBefore(exceptionHandlerFilter, JwtAuthenticationFilter.class)
//				.accessDecisionManager(affirmativeBased())
				.build();

	}

	@Bean
	public RoleHierarchyImpl roleHierarchy() {
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
//		roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_LIBMANAGER\n" + "ROLE_LIBMANAGER > ROLE_CHALLENGEMANAGER\n" + "ROLE_CHALLENGEMANAGER > ROLE_USER\n" + "ROLE_USER > ROLE_TEMP");
//		roleHierarchy.setHierarchy("ADMIN > LIBMANAGER\n" + "LIBMANAGER > CHALLENGEMANAGER\n" + "CHALLENGEMANAGER > USER\n" + "USER > TEMP");
		
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

//	@Bean
//    public PermitAllFilter customFilterSecurityInterceptor() throws Exception {
//        PermitAllFilter permitAllFilter = new PermitAllFilter(permitAllResources);
//        permitAllFilter.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource());
//        permitAllFilter.setAccessDecisionManager(affirmativeBased());
//        permitAllFilter.setAuthenticationManager(authenticationManagerBean());
//        return permitAllFilter;
//    }
	

}
