package com.planner.godsaeng.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planner.godsaeng.dto.ChallengeDTO;
import com.planner.godsaeng.entity.Challenge;
import com.planner.godsaeng.repository.ChallengeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChallengeService {
	@Autowired
	private final ChallengeRepository challengeRepository;
	
	Challenge challenge = null;
	//챌린지 CREATE
	
	public boolean InsertChallenge(ChallengeDTO d) {
		challenge = Challenge.builder()
			.c_id(d.getC_id())
			.c_name(d.getC_name())
			.c_content(d.getC_content())
			.c_startdate(d.getC_startdate())
			.c_enddate(d.getC_enddate())
			.c_numberofparticipants(d.getC_numberofparticipants())
			.c_category(d.getC_category())
			.c_thumbnails(d.getC_thumbnails())
			.c_introduction(d.getC_introduction())
			.c_fee(d.getC_fee())
			.c_numberofphoto(d.getC_numberofphoto())
			.c_typeofverify(d.getC_typeofverify())
			.c_typeoffrequency(d.getC_typeoffrequency())
			.c_frequency(d.getC_frequency())
			.c_score(d.getC_score())
			.build();
		
		try {
			challengeRepository.save(challenge);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
//	public List<ChallengeDTO>ReadPopularChallenge(St)
			
		
	}

}
