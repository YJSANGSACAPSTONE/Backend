package com.planner.godsaeng.service;

import java.io.File;
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

import javax.transaction.Transactional;

import org.apache.http.entity.ContentType;
import org.apache.tomcat.util.net.jsse.PEMFile;
import org.hibernate.internal.build.AllowPrintStacktrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.planner.godsaeng.dto.ChallengeDTO;
import com.planner.godsaeng.dto.ChallengeStatusDTO;
import com.planner.godsaeng.entity.Challenge;
import com.planner.godsaeng.repository.ChallengeRepository;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;









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
		 String path = "/img/challengeimg";
		 if (!thumbnail.isEmpty()) {
			 String fileName = thumbnail.getOriginalFilename();
			 File dest = new File(path + File.separator + fileName);
			 thumbnail.transferTo(dest);
			 dto.setThumbnailData(thumbnail);

			 String thumbnailPath = path + File.separator + fileName;
			 dto.setC_thumbnails(thumbnailPath);
		 }
		 Challenge entity = dtoToEntity(dto);
		 challengeRepository.save(entity);

		 return true;
	 }
	 public List<ChallengeDTO>ReadPopularChallenge(){


		 List<Challenge>popularListEntity=challengeRepository.findAllByOrderByCnumberofparticipantsDesc();
		 List<ChallengeDTO>popularList=new ArrayList<>();

		 for(Challenge e : popularListEntity) {
			 ChallengeDTO dto = entityToDto(e);
			 String thumbnailPath = e.getCthumbnails();
			 File thumbnailFile = new File(thumbnailPath);
			 try {
				 MultipartFile thumbnailData = new MockMultipartFile(thumbnailFile.getName(), thumbnailFile.getName(),
						 ContentType.APPLICATION_OCTET_STREAM.toString(), Files.readAllBytes(thumbnailFile.toPath()));
				 dto.setThumbnailData(thumbnailData);
				 popularList.add(dto);
			 } catch (IOException c) {
				 // 예외 처리 로직
				 c.printStackTrace();
			 }
		 }
		 return popularList;
	 }
	//최신챌린지 조회R2 - querytest 완료
	 public List<ChallengeDTO>ReadRecentChallenge(){
		 List<Challenge>recentListEntity = challengeRepository.findAllByOrderByCstartdateDesc();
		 List<ChallengeDTO>recentList = new ArrayList<>();
		 for(Challenge e : recentListEntity) {
			 ChallengeDTO dto = entityToDto(e);
			 String thumbnailPath = e.getCthumbnails();
			 File thumbnailFile = new File(thumbnailPath);
			 try {
				 MultipartFile thumbnailData = new MockMultipartFile(thumbnailFile.getName(), thumbnailFile.getName(),
						 ContentType.APPLICATION_OCTET_STREAM.toString(), Files.readAllBytes(thumbnailFile.toPath()));
				 dto.setThumbnailData(thumbnailData);
				 recentList.add(dto);
			 } catch (IOException c) {
				 // 예외 처리 로직
				 c.printStackTrace();
			 }
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
		ChallengeDTO dto = entityToDto(e);
		String thumbnailPath = e.getCthumbnails();
	    File thumbnailFile = new File(thumbnailPath);
	    try {
	        MultipartFile thumbnailData = new MockMultipartFile(thumbnailFile.getName(), thumbnailFile.getName(),
	                ContentType.APPLICATION_OCTET_STREAM.toString(), Files.readAllBytes(thumbnailFile.toPath()));
	        dto.setThumbnailData(thumbnailData);
	        myList.add(dto);
	    } catch (IOException c) {
	        // 예외 처리 로직
	        c.printStackTrace();
	    	}
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
			File thumbnailFile = new File(thumbnailPath);
			try {
				MultipartFile thumbnailData = new MockMultipartFile(thumbnailFile.getName(), thumbnailFile.getName(),
						ContentType.APPLICATION_OCTET_STREAM.toString(), Files.readAllBytes(thumbnailFile.toPath()));
				dto.setThumbnailData(thumbnailData);
				ChallengeList.add(dto);
			} catch (IOException c) {
				// 예외 처리 로직
				c.printStackTrace();
			}
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
}
