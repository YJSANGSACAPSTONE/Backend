package com.planner.godsaeng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.planner.godsaeng.dto.ChallengeDTO;
import com.planner.godsaeng.service.ChallengeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/challenge")
@RequiredArgsConstructor
public class ChallengeController {
	@Autowired
	ChallengeService service;
	
	@PostMapping("/addchallenge")
	public ResponseEntity<Boolean>addChallenge(@ModelAttribute ChallengeDTO d){
		boolean isAddSuccessed =true;
	}
	
	
	
	
	//황주바보
	//황주바보
	
	

}
