package com.planner.godsaeng.service;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.planner.godsaeng.dto.ChallengeVerifyDTO;
import com.planner.godsaeng.dto.ZepRequestDTO;
import com.planner.godsaeng.entity.Challenge;
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
	

	ChallengeVerify challengeverify;
	
	public Boolean VerifyMetaChallenge(ZepRequestDTO m) {
		
		//dto에 담긴 챌린지id 정보로 챌린지id를 가져옴.
		Long cid = m.getCid();
		System.out.println("cid : " + cid);
		//담겨져온 zepid를 역산하여 uid를 가져옴
		String uid = userRepository.findUidByUzepid(m.getCvzepid());
		//가져온 uid와 cid를 토대로 cpid를 추정하여 저장함.
		System.out.println("cid: " + cid + "uid: " + uid);
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
	
	public Boolean InsertNormalChallengeVerifyData(ChallengeVerifyDTO m, MultipartFile verifyphoto) throws IOException{
		String path = ResourceUtils.getFile("src/main/resources/static/img/challengeverifyimg").getAbsolutePath();
		if (!verifyphoto.isEmpty()) {
			String fileName = verifyphoto.getOriginalFilename();
			File dest = new File(path + File.separator + fileName);
			verifyphoto.transferTo(dest);
			m.setVerifyPhoto(verifyphoto);
			m.setCvphoto("/img/challengeverifyimg/" + fileName);
		}
		ChallengeVerify entity = dtoToEntity(m);
		challengeVerifyRepository.save(entity);
		return true;
		
	}
	public ChallengeVerifyDTO entityToDto(ChallengeVerify v) {
		return ChallengeVerifyDTO.builder()
				.cvid(v.getCvid())
				.cvphoto(v.getCvphoto())
				.cvsuccessornot(v.getCvsuccessornot())
				.cvtime(v.getCvtime())
				.build();
	}
	public ChallengeVerify dtoToEntity(ChallengeVerifyDTO v) {
		return ChallengeVerify.builder()
				.cvid(v.getCvid())
				.cvphoto(v.getCvphoto())
				.cvsuccessornot(v.getCvsuccessornot())
				.cvtime(v.getCvtime())
				.cvzepid(null)
				.build();
	}
}
