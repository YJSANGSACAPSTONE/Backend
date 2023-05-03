package com.planner.godsaeng;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.planner.godsaeng.dto.ChallengeDTO;
import com.planner.godsaeng.dto.ChallengeStatusDTO;
import com.planner.godsaeng.entity.Challenge;
import com.planner.godsaeng.repository.ChallengeRepository;

//@SpringBootTest
//public class QueryTest2 {
//	@Test
//	void contextLoads() {
//		
//		List<ChallengeStatusDTO> challenge = new ArrayList<>();
//		
//		challenge = myChallengeProgress("sanghee_ok@naver.com");
//		
//		for(ChallengeStatusDTO c : challenge) {
//			System.out.println(c.getCname());
//			System.out.println(c.getCvsuccesscount());
//			System.out.println(c.getCenddate());
//			}
//		}
//	@Autowired
//	private ChallengeRepository challengeRepository;
//	
//	 public List<ChallengeStatusDTO> myChallengeProgress(String uid) {
//	        return challengeRepository.myChallengeProgress(uid);
//	    }
//
//}
