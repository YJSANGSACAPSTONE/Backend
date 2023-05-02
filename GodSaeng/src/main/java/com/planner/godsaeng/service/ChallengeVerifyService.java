package com.planner.godsaeng.service;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.planner.godsaeng.dto.ChallengeVerifyDTO;
import com.planner.godsaeng.dto.ZepRequestDTO;
import com.planner.godsaeng.entity.ChallengeParticipate;
import com.planner.godsaeng.entity.ChallengeVerify;
import com.planner.godsaeng.repository.ChallengeParticipateRepository;
import com.planner.godsaeng.repository.ChallengeRepository;
import com.planner.godsaeng.repository.ChallengeVerifyRepository;
import com.planner.godsaeng.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChallengeVerifyService {
	
	private final ChallengeParticipateRepository challengeparticipateRepository;
	private final UserRepository userRepository;
	private final ChallengeRepository challengeRepository;
	private final ChallengeVerifyRepository challengeVerifyRepository;
	
	ChallengeVerify challengeverify = null;
	
	public Boolean VerifyMetaChallenge(ZepRequestDTO m) {
		Long cid = m.getCid();
		String uid = "sanghee";
//		String uid = userRepository.get
		Long cpid = challengeparticipateRepository.findCpidByCidAndUid(cid, uid);
		
		Optional<ChallengeParticipate> challengeparticipate = challengeparticipateRepository.findById(cpid);
		challengeverify = ChallengeVerify.builder()
				.challengeParticipate(challengeparticipate.get())
				.cvphoto(null)
				.cvsuccessornot(m.getCvsuccessornot())
				.cvtime(m.getCvtime())
				.cvzepid(m.getCvzepid())
				.build();
		try {
			challengeVerifyRepository.save(challengeverify);
			return true;
		}catch(Exception e) {
			return false;
		}
				
				
		
	}

}
