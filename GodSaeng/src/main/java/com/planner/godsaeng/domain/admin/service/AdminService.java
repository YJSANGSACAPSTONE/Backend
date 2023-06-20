package com.planner.godsaeng.domain.admin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.planner.godsaeng.dto.BoardDTO;
import com.planner.godsaeng.dto.ChallengeDTO;
import com.planner.godsaeng.dto.ChallengeVerifyDTO;
import com.planner.godsaeng.entity.Board;
import com.planner.godsaeng.entity.Challenge;
import com.planner.godsaeng.entity.ChallengeVerify;
import com.planner.godsaeng.entity.User;
import com.planner.godsaeng.repository.BoardRepository;
import com.planner.godsaeng.repository.ChallengeRepository;
import com.planner.godsaeng.repository.ChallengeVerifyRepository;
import com.planner.godsaeng.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminService {
	private final ChallengeRepository challengeRepository;
	private final ChallengeVerifyRepository cvRepository;
	private final UserRepository userRepository;
	private final BoardRepository boardRepository;
	
	//현재 진행중인 챌린지 리스트
	public List<ChallengeDTO>findActiveChallenges(){
		List<Challenge> activeListentity = challengeRepository.findActiveChallenges();
		List<ChallengeDTO> activeList = new ArrayList<>();
		for(Challenge a:activeListentity) {
			ChallengeDTO dto = 
				ChallengeDTO.builder()
				.c_id(a.getCid())
				.c_name(a.getCname())
				.c_startdate(a.getCstartdate())
				.c_enddate(a.getCenddate())
				.c_typeofverify(a.getCtypeofverify())
				.c_numberofparticipants(a.getCnumberofparticipants())
				.build();
			activeList.add(dto);
		}
		return activeList;
	}
	//챌린지 리스트에서 챌린지명을 기반으로 도착한 인증정보들을 display
	public List<ChallengeVerifyDTO>findVerifyLists(Long cid){
		List<ChallengeVerify>verifyListentity = cvRepository.findByCid(cid);
		List<ChallengeVerifyDTO>verifyList = new ArrayList<>();
		
		for(ChallengeVerify a : verifyListentity) {
			ChallengeVerifyDTO dto =
					ChallengeVerifyDTO.builder()
					.cvid(a.getCvid())
					.cvphoto(a.getCvphoto())
					.cvsuccessornot(a.getCvsuccessornot())
					.cvtime(a.getCvtime())
					.build();
			verifyList.add(dto);
		}
		return verifyList;
	}
	//챌린지 참가 관련 사진 조회 후 인증
	public Boolean verifyParticipate(Long cvid){
		Optional<ChallengeVerify> verifyData = cvRepository.findById(cvid);
		if (verifyData.isPresent()) { 
		ChallengeVerify data = verifyData.get();
		data.changeSuccessData(1);
			cvRepository.save(data);
			return true;
		}
		return false;
	}
//	public Boolean gradeupUser(String uid) {
//		Optional<User>userData = userRepository.findById(uid);
//		if(userData.isPresent()) {
//			User data = userData.get();
//			
//		}
//	}
	//관리자가 게시판 개설
	public void CreateBoard(BoardDTO a, String uid) {
		Optional<User> user = userRepository.findById(uid);
		User userdata = user.get();
		Board info = 
				Board.builder()
				.bname(a.getB_name())
				.user(userdata)
				.build();
		boardRepository.save(info);
	}
	
	public List<Object[]>adminChallengeInfo(){
		List<Object[]>a = cvRepository.getChallengeStatistics();
		return a;
		
	}
}
	
