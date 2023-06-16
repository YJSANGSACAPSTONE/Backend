package com.planner.godsaeng.domain.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planner.godsaeng.domain.admin.service.AdminService;
import com.planner.godsaeng.dto.ChallengeDTO;
import com.planner.godsaeng.dto.ChallengeVerifyDTO;
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
	private final AdminService adminService;
	
	//챌린지 인증내역에 들어갔을 때, 관리자가 볼 수 있는 페이지
	@RequestMapping("/challengelist")
	public ResponseEntity<List<ChallengeDTO>>listChallenge(@AuthenticationPrincipal JwtAuthentication user){
		List<ChallengeDTO>result = adminService.findActiveChallenges();
		if(!result.isEmpty()) {
			return ResponseEntity.ok(result);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	@RequestMapping("/verifylist")
	public ResponseEntity<List<ChallengeVerifyDTO>>verifyNormalChallenge(@AuthenticationPrincipal JwtAuthentication user,Long cid){
		List<ChallengeVerifyDTO>result = adminService.findVerifyLists(cid);
		if(!result.isEmpty()) {
			return ResponseEntity.ok(result);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	@RequestMapping("/verifythischallenge")
	public ResponseEntity<Boolean>verifyThisChallenge(@AuthenticationPrincipal JwtAuthentication user, Long cvid){
		boolean isVerifySuccessed = adminService.verifyParticipate(cvid);
		if(isVerifySuccessed = true) {
			return ResponseEntity.ok(true);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}
	}
	
	
	

}
