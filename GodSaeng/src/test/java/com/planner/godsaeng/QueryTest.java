package com.planner.godsaeng;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.planner.godsaeng.dto.ChallengeDTO;
import com.planner.godsaeng.entity.Challenge;
import com.planner.godsaeng.repository.ChallengeRepository;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
@SpringBootTest
public class QueryTest {
//	PlanDTO plan = null;
	@Test
	void contextLoads() {
		
		List<ChallengeDTO> challenge = ReadPopularChallenge();
		
		for(ChallengeDTO c : challenge) {
			System.out.println(c.getC_name());
			System.out.println(c.getC_startdate());
			System.out.println(c.getC_numberofparticipants());
			}
		}
	@Autowired
	private ChallengeRepository challengeRepository;
	public List<ChallengeDTO>ReadPopularChallenge(){
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
		//종료된 챌린지는 제외시켜야함 --> 미완성(2023.04.18)
	}
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

}
