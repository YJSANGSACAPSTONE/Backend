package com.planner.godsaeng.service;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.planner.godsaeng.dto.ChallengeDTO;
import com.planner.godsaeng.dto.ChallengeParticipateDTO;
import com.planner.godsaeng.entity.Challenge;
import com.planner.godsaeng.entity.ChallengeParticipate;
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
		Optional<Challenge>challenge = challengeRepository.findById(m.getC_id());
		challengeparticipate = ChallengeParticipate.builder()
				.user(user.get())
				.challenge(challenge.get())
				.cpfinalsuccess(0)
				.build();
		try {
			System.out.println(uid + "참가비 : " + participatefee);
			userRepository.updateDeposit(uid, participatefee);
			System.out.println("여기까지 흐름이 오니?"); 
			challengeparticipateRepository.save(challengeparticipate);
			System.out.println("여기는 오니?"); 
			return true;
		}catch(Exception e) {
			return false;
		}
		
	}
	
	public Boolean LeftChallenge(ChallengeParticipateDTO m) {
		return false;
	}
	
	
	

}
