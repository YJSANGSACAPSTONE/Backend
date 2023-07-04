package com.planner.godsaeng.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planner.godsaeng.dto.ChallengeDataDTO;
import com.planner.godsaeng.service.ChallengeVerifyService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/zep")
@RequiredArgsConstructor
public class ZepWorldDataController {
	
	private final ChallengeVerifyService verifyService;
	
	@PostMapping("/miraclemorning")
	public ResponseEntity <List<Map<String, Object>>> getChallengeRank(@RequestBody ChallengeDataDTO a){
		
		List<Map<String,Object>>data = new ArrayList<>();
		data = verifyService.getChallengeRank(a.getC_id());
		
		if(!data.isEmpty()) {
			return ResponseEntity.ok(data);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		
	}
	@PostMapping("/codingchallenge")
	public ResponseEntity<List<Map<String,Object>>>getCodingChallengeRank(@RequestBody ChallengeDataDTO a){
		
		List<Map<String,Object>>data = new ArrayList<>();
		data = verifyService.getCodingChallengeRank(a.getC_id());
		
		if(!data.isEmpty()) {
			return ResponseEntity.ok(data);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
