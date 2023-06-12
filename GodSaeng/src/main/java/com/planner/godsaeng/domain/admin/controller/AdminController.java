package com.planner.godsaeng.domain.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planner.godsaeng.security.jwt.JwtAuthentication;
import com.planner.godsaeng.service.ChallengeService;
import com.planner.godsaeng.service.ChallengeVerifyService;
import com.planner.godsaeng.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RequestMapping("/admin")
@Slf4j
@RestController
public class AdminController {
	
	
	private final ChallengeVerifyService verifyService;
	private final ChallengeService challengeService;
	private final UserService userService;
	
	//챌린지 인증내역에 들어갔을 때, 관리자가 볼 수 있는 페이지
	public ResponseEntity<T>listChallenge(@AuthenticationPrincipal JwtAuthentication user){
	}
	
	
	

}
