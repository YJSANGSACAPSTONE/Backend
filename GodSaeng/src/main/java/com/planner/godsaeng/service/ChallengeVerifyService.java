package com.planner.godsaeng.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
import com.planner.godsaeng.entity.ChallengeParticipateId;
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
		Optional<ChallengeParticipate> challengeparticipate = challengeparticipateRepository.findById(new ChallengeParticipateId(uid,cid));
		
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
			m.setCvsuccessornot(0);
			m.setCvtime(LocalDateTime.now());
			m.setVerifyPhoto(verifyphoto);
			m.setCvphoto("/img/challengeverifyimg/" + fileName);
			
		}
		ChallengeVerify entity = dtoToEntity(m);
		ChallengeParticipateId id = new ChallengeParticipateId(m.getUid(),m.getCid());
		Optional<ChallengeParticipate> cp = challengeparticipateRepository.findById(id);
		entity.setChallengeParticipate(cp.get());
		challengeVerifyRepository.save(entity);
		return true;
		
	}
	
	public Map<String, Long> countVerifyByMonthRange() {
	    LocalDate startDate = LocalDate.of(LocalDate.now().getYear(), 1, 1); // January 1st of the current year
	    LocalDate endDate = LocalDate.now().withDayOfMonth(1); // First day of the current month

	    Map<String, Long> result = new HashMap<>();

	    while (startDate.isBefore(endDate) || startDate.isEqual(endDate)) {
	        LocalDateTime currentMonthEnd = startDate.atTime(23, 59, 59);

	        Long count = challengeVerifyRepository.countVerifyByDateRange(
	                startDate.atStartOfDay(), currentMonthEnd);

	        result.put(startDate.getMonth().toString(), count);

	        startDate = startDate.plusMonths(1);
	    }

	    return result;
	}
	
	public Map<LocalDate, Long> countVerifyByRecentDays() {
	    LocalDateTime startDate = LocalDateTime.now().minusDays(14); // 현재 날짜에서 14일 전까지의 시작 일시

	    List<Object[]> data = challengeVerifyRepository.countVerifyByRecentDays(startDate);

	    Map<LocalDate, Long> result = new LinkedHashMap<>();
	    for (Object[] row : data) {
	        LocalDateTime dateTime = (LocalDateTime) row[0];
	        Long count = (Long) row[1];
	        LocalDate date = dateTime.toLocalDate(); // LocalDateTime에서 LocalDate로 변환
	        result.put(date, count);
	    }

	    return result;
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
				
				.cvphoto(v.getCvphoto())
				.cvsuccessornot(v.getCvsuccessornot())
				.cvtime(v.getCvtime())
				.cvzepid(null)
				.build();
	}
}
