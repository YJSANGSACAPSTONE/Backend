package com.planner.godsaeng;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.planner.godsaeng.dto.ChallengeDTO;
import com.planner.godsaeng.dto.ChallengeStatusDTO;
import com.planner.godsaeng.entity.Challenge;
import com.planner.godsaeng.repository.ChallengeRepository;
import com.planner.godsaeng.service.ChallengeDataConverter;

@SpringBootTest
public class QueryTest3 {
	
	@Autowired
    private ChallengeDataConverter challengeStatusConverter;
	
	@Autowired
	    private ChallengeRepository challengeRepository;
	
	 @Test
	    void contextLoads() {

	        List<ChallengeStatusDTO> challenge = new ArrayList<>();

	        String uid= "sanghee_ok@naver.com";
	        challenge = myChallengeProgress(uid);
	        if(challenge.isEmpty()) {
	            System.out.println("haha");
	        } else {
	            System.out.println("fuck");
	        }

	        for(ChallengeStatusDTO c : challenge) {
	            System.out.println(c.getCname());
	            System.out.println(c.getCvsuccesscount());
	            System.out.println(c.getCenddate());
	        }
	    }

	    public List<ChallengeStatusDTO> myChallengeProgress(String uid) {
	        List<Object[]> resultList = challengeRepository.myChallengeProgress(uid);
	        return resultList.stream()
	                .map(challengeStatusConverter::convert)
	                .collect(Collectors.toList());
	    }
	}


