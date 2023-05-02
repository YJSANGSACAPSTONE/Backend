package com.planner.godsaeng;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.planner.godsaeng.dto.ChallengeDTO;
import com.planner.godsaeng.entity.Challenge;
import com.planner.godsaeng.repository.ChallengeRepository;

@SpringBootTest
public class QueryTest2 {
	@Test
	void contextLoads() {
		
		List<ChallengeDTO> challenge = ReadMyChallenge("sanghee_ok@naver.com");
		
		for(ChallengeDTO c : challenge) {
			System.out.println(c.getC_name());
			System.out.println(c.getC_startdate());
			System.out.println(c.getC_numberofparticipants());
			}
		}
	@Autowired
	private ChallengeRepository challengeRepository;
	
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

}
