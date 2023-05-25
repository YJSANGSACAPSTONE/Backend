package com.planner.godsaeng.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.planner.godsaeng.dto.ChallengeDTO;
import com.planner.godsaeng.dto.ChallengeStatusDTO;
import com.planner.godsaeng.entity.Challenge;
import com.planner.godsaeng.entity.User;
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
	@Transactional
	public boolean InsertChallenge(ChallengeDTO dto, MultipartFile thumbnail) throws IOException {
		String path = ResourceUtils.getFile("src/main/resources/static/img/challengeimg").getAbsolutePath();
		if (!thumbnail.isEmpty()) {
			String fileName = thumbnail.getOriginalFilename();
			File dest = new File(path + File.separator + fileName);
			thumbnail.transferTo(dest);
			dto.setThumbnailData(thumbnail);
			dto.setC_thumbnails("/img/challengeimg/" + fileName);
		}
		Challenge entity = dtoToEntity(dto);
		challengeRepository.save(entity);

		return true;
	}
	public List<ChallengeDTO>ReadAllChallenge(){
		List<Challenge>allListEntity = challengeRepository.findAll();
		List<ChallengeDTO>allList = new ArrayList<>();
		for(Challenge e: allListEntity) {
			ChallengeDTO dto = entityToDto(e);
			allList.add(dto);
		}
		return allList;
	}

	public List<ChallengeDTO> ReadPopularChallenge() {
		List<Challenge> popularListEntity = challengeRepository.findAllByOrderByCnumberofparticipantsDesc();
		List<ChallengeDTO> popularList = new ArrayList<>();
		for (Challenge e : popularListEntity) {
		ChallengeDTO dto = entityToDto(e);
		String thumbnailPath = e.getCthumbnails();
		System.out.println("thumbnailPath:인기리스트 " + thumbnailPath);
		dto.setC_thumbnails(thumbnailPath);
		popularList.add(dto);
		}
		return popularList;
		}
	//최신챌린지 조회R2 - querytest 완료
	public List<ChallengeDTO> ReadRecentChallenge() {
	    List<Challenge> recentListEntity = challengeRepository.findAllByOrderByCstartdateDesc();
	    List<ChallengeDTO> recentList = new ArrayList<>();
	    for (Challenge e : recentListEntity) {
	        ChallengeDTO dto = entityToDto(e);
	        String thumbnailPath = e.getCthumbnails();
	        dto.setC_thumbnails(thumbnailPath);
	        recentList.add(dto);
	    }
	    return recentList;
	}

	
	String uid = "hwangjoo";
	//내가 참가중인 챌린지 조회R3
	//쿼리 검증 완료 - challenge와 challengeparticipate join하여 데이터출력.

	public List<ChallengeDTO> ReadMyChallenge(String uid) {
		List<Challenge> myListEntity = challengeRepository.findChallengeByUid(uid);
		List<ChallengeDTO> myList = new ArrayList<>();
		for (Challenge e : myListEntity) {
			ChallengeDTO dto = entityToDto(e);
			dto.setThumbnailData(null); // MultipartFile 객체를 null로 설정
			dto.setC_thumbnails(e.getCthumbnails()); // 이미지 경로만 설정
			myList.add(dto);

		}
		return myList;
	}
	//여기까지 챌린지 R
	//여기부터 CRUD-U 챌린지 UPDATE
	public boolean UpdateChallenge(ChallengeDTO d) {
		Optional<Challenge>result = challengeRepository.findById(d.getC_id());
		if(result.isPresent()) {
			dtoToEntity(d);
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
		List<ChallengeDTO>ChallengeList = new ArrayList<>();
		for(Challenge e : challengeEntity) {
			ChallengeDTO dto = entityToDto(e);
			ChallengeList.add(dto);
			String thumbnailPath = e.getCthumbnails();
				dto.setThumbnailData(null);
				ChallengeList.add(dto);
				// 예외 처리 로직		
		}
		return ChallengeList;
	}
	public List<ChallengeStatusDTO> myChallengeProgress(String uid) {
		List<Object[]> resultList = challengeRepository.myChallengeProgress(uid);
		return resultList.stream()
				.map(challengeStatusConverter::convert)
				.collect(Collectors.toList());
	}
	public ChallengeDTO entityToDto(Challenge e) {
		return ChallengeDTO.builder()
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
				.build();
	}
	public Challenge dtoToEntity(ChallengeDTO d) {
		return Challenge.builder()
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
	}
	
	public Optional<Challenge> SearchCid(Long cid) {
		   Optional<Challenge> challengeEntity = challengeRepository.findById(cid);
		   return challengeEntity;
	   }
}
