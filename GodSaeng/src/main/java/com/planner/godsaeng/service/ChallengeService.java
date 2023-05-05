package com.planner.godsaeng.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.tomcat.util.net.jsse.PEMFile;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.planner.godsaeng.dto.ChallengeDTO;
import com.planner.godsaeng.dto.ChallengeStatusDTO;
import com.planner.godsaeng.entity.Challenge;
import com.planner.godsaeng.repository.ChallengeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChallengeService {
	
	private final ChallengeRepository challengeRepository;
	
	@Autowired
    private ChallengeDataConverter challengeStatusConverter;
	
	Challenge challenge = null;
	//여기서부터 CRUD-C 챌린지 CREATE
	
	public boolean InsertChallenge(ChallengeDTO d, MultipartFile file) {
		try {
		 
		byte[] thumbnailData = file.getBytes(); // MultipartFile에서 바이트 배열로 파일 데이터 읽어오기

         // 파일 저장 로직
        String fileName = file.getOriginalFilename();
        Path path = Paths.get("/uploads/" + fileName);
        Files.write(path, thumbnailData);
        challenge = Challenge.builder()
			.cid(d.getC_id())
			.cname(d.getC_name())
			.ccontent(d.getC_content())
			.cstartdate(d.getC_startdate())
			.cenddate(d.getC_enddate())
			.cnumberofparticipants(d.getC_numberofparticipants())
			.ccategory(d.getC_category())
			.cintroduction(d.getC_introduction())
			.cfee(d.getC_fee())
			.cnumberofphoto(d.getC_numberofphoto())
			.ctypeofverify(d.getC_typeofverify())
			.ctypeoffrequency(d.getC_typeoffrequency())
			.cfrequency(d.getC_frequency())
			.cscore(d.getC_score())
			.cthumbnails(thumbnailData)
			.build();
		
			challengeRepository.save(challenge);
			
			return true;
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
	}
	//insert문 끝
	//여기서부터 CRUD - Read
	//인기챌린지 (Read)조회 - querytest완료
	//종료된 챌린지는 제외시켜야함 --> 미완성(2023.04.18) 
	//--> 현재 날짜가 종료 날짜를 초과한 시점이면 안될 듯. 혹은 종료된 챌린지인지 아닌지 알 수 있는 attribute가 필요.
	public List<ChallengeDTO>ReadPopularChallenge(){
		
		try {
			List<Challenge>popularListEntity=challengeRepository.findAllByOrderByCnumberofparticipantsDesc();
			List<ChallengeDTO>popularList=new ArrayList<>();
			
			for(Challenge e : popularListEntity) {
				popularList.add(
						ChallengeDTO.builder()
						.c_id(e.getCid())
						.c_name(e.getCname())
						.c_content(e.getCcontent())
						.c_startdate(e.getCstartdate())
						.c_enddate(e.getCenddate())
						.c_numberofparticipants(e.getCnumberofparticipants())
						.c_category(e.getCcategory())
						.c_thumbnails(e.getCthumbnails())
						.c_introduction(e.getCintroduction())
						.c_fee(e.getCfee())
						.c_numberofphoto(e.getCnumberofphoto())
						.c_typeofverify(e.getCtypeofverify())
						.c_typeoffrequency(e.getCtypeoffrequency())
						.c_frequency(e.getCfrequency())
						.c_score(e.getCscore())
						.build()
				);
			}
			return popularList;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		
	}
	//최신챌린지 조회R2 - querytest 완료
	public List<ChallengeDTO>ReadRecentChallenge(){
		List<Challenge>recentListEntity = challengeRepository.findAllByOrderByCstartdateDesc();
		List<ChallengeDTO>recentList = new ArrayList<>();
		for(Challenge e : recentListEntity) {
			recentList.add(
					ChallengeDTO.builder()
					.c_id(e.getCid())
					.c_name(e.getCname())
					.c_content(e.getCcontent())
					.c_startdate(e.getCstartdate())
					.c_enddate(e.getCenddate())
					.c_numberofparticipants(e.getCnumberofparticipants())
					.c_category(e.getCcategory())
					.c_thumbnails(e.getCthumbnails())
					.c_introduction(e.getCintroduction())
					.c_fee(e.getCfee())
					.c_numberofphoto(e.getCnumberofphoto())
					.c_typeofverify(e.getCtypeofverify())
					.c_typeoffrequency(e.getCtypeoffrequency())
					.c_frequency(e.getCfrequency())
					.c_score(e.getCscore())
					.build()
			);
		}
		return recentList;
	}
	
	String uid = "hwangjoo";
	//내가 참가중인 챌린지 조회R3
	//쿼리 검증 완료 - challenge와 challengeparticipate join하여 데이터출력.
	public List<ChallengeDTO>ReadMyChallenge(String uid){
		
		List<Challenge>myListEntity = challengeRepository.findChallengeByUid(uid);
		List<ChallengeDTO>myList = new ArrayList<>();
		for(Challenge e : myListEntity) {
			myList.add(
					ChallengeDTO.builder()
					.c_id(e.getCid())
					.c_name(e.getCname())
					.c_content(e.getCcontent())
					.c_startdate(e.getCstartdate())
					.c_enddate(e.getCenddate())
					.c_numberofparticipants(e.getCnumberofparticipants())
					.c_category(e.getCcategory())
					.c_thumbnails(e.getCthumbnails())
					.c_introduction(e.getCintroduction())
					.c_fee(e.getCfee())
					.c_numberofphoto(e.getCnumberofphoto())
					.c_typeofverify(e.getCtypeofverify())
					.c_typeoffrequency(e.getCtypeoffrequency())
					.c_frequency(e.getCfrequency())
					.c_score(e.getCscore())
					.build()
			);
			
		}
		return myList;
	}
	//여기까지 챌린지 R
	//여기부터 CRUD-U 챌린지 UPDATE
	public boolean UpdateChallenge(ChallengeDTO d) {
		Optional<Challenge>result = challengeRepository.findById(d.getC_id());
		if(result.isPresent()) {
			challenge = Challenge.builder()
				.cid(d.getC_id())
				.cname(d.getC_name())
				.ccontent(d.getC_content())
				.cstartdate(d.getC_startdate())
				.cenddate(d.getC_enddate())
				.cnumberofparticipants(d.getC_numberofparticipants())
				.ccategory(d.getC_category())
				.cthumbnails(d.getC_thumbnails())
				.cintroduction(d.getC_introduction())
				.cfee(d.getC_fee())
				.cnumberofphoto(d.getC_numberofphoto())
				.ctypeofverify(d.getC_typeofverify())
				.ctypeoffrequency(d.getC_typeoffrequency())
				.cfrequency(d.getC_frequency())
				.cscore(d.getC_score())
				.build();
				
		try {
			challengeRepository.save(challenge);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
			}
		}else {
			return false;
		}
		
	}
	
	
	//여기부터 CRUD-D 챌린지 DELETE	
	public boolean DeleteChallenge(Long c_id) {
		try {
			challengeRepository.deleteById(c_id);
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}	
	}
	
	public List<ChallengeDTO>ReadChallenge(Long c_id){
		List<Challenge>challengeEntity = challengeRepository.findByCid(c_id);
		List<ChallengeDTO>myList = new ArrayList<>();
		for(Challenge e : challengeEntity) {
			myList.add(
					ChallengeDTO.builder()
					.c_id(e.getCid())
					.c_name(e.getCname())
					.c_content(e.getCcontent())
					.c_startdate(e.getCstartdate())
					.c_enddate(e.getCenddate())
					.c_numberofparticipants(e.getCnumberofparticipants())
					.c_category(e.getCcategory())
					.c_thumbnails(e.getCthumbnails())
					.c_introduction(e.getCintroduction())
					.c_fee(e.getCfee())
					.c_numberofphoto(e.getCnumberofphoto())
					.c_typeofverify(e.getCtypeofverify())
					.c_typeoffrequency(e.getCtypeoffrequency())
					.c_frequency(e.getCfrequency())
					.c_score(e.getCscore())
					.build()
			);	
	}
		return myList;

	}
	public List<ChallengeStatusDTO> myChallengeProgress(String uid) {
		List<Object[]> resultList = challengeRepository.myChallengeProgress(uid);
        return resultList.stream()
                .map(challengeStatusConverter::convert)
                .collect(Collectors.toList());
}
}
