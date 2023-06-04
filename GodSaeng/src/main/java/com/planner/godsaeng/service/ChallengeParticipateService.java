package com.planner.godsaeng.service;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.planner.godsaeng.dto.ChallengeDTO;
import com.planner.godsaeng.dto.ChallengeParticipateDTO;
import com.planner.godsaeng.entity.Challenge;
import com.planner.godsaeng.entity.ChallengeParticipate;
import com.planner.godsaeng.entity.ChallengeParticipateId;
import com.planner.godsaeng.entity.User;
import com.planner.godsaeng.repository.ChallengeParticipateRepository;
import com.planner.godsaeng.repository.ChallengeRepository;
import com.planner.godsaeng.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChallengeParticipateService {
	
	private final ChallengeParticipateRepository challengeparticipateRepository;
	private final UserRepository userRepository;
	private final ChallengeRepository challengeRepository;
	
	ChallengeParticipate challengeparticipate = null;
	
	public Boolean ParticipateChallenge(ChallengeDTO m,String uid) {
		
		Integer participatefee = m.getC_fee();
		
		Optional<User>user = userRepository.findById(uid);
		if(user.get().getUdeposit() - participatefee >=0) {
		Optional<Challenge>challenge = challengeRepository.findById(m.getC_id());
		challengeparticipate = ChallengeParticipate.builder()
				.user(user.get())
				.challenge(challenge.get())
				.cpfinalsuccess(0)
				.build();
		try {
			
			userRepository.updateDeposit(uid, participatefee);
			challengeparticipateRepository.save(challengeparticipate);
			
			return true;
		}catch(Exception e) {
			return false;
		}
			}else {
			return false;
		}
		
	}
	
	public Boolean LeftChallenge(Long cid, String uid) {
		
		ChallengeParticipateId cpid = new ChallengeParticipateId(uid, cid);
		challengeparticipateRepository.deleteById(cpid);
		Optional<User>user = userRepository.findById(uid);
		return true;
	}
	
	
	

}
