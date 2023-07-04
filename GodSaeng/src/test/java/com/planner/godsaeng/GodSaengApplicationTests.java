package com.planner.godsaeng;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.planner.godsaeng.dto.ChallengeDTO;
import com.planner.godsaeng.dto.PlanDTO;
import com.planner.godsaeng.entity.Challenge;
import com.planner.godsaeng.entity.Plan;
import com.planner.godsaeng.repository.ChallengeRepository;



@SpringBootTest
class GodsaengApplicationTests {
//	PlanDTO plan = null;
	@Test
	void contextLoads() {
		
		List<ChallengeDTO> challenge = ReadRecentChallenge();
		
		for(ChallengeDTO c : challenge) {
			System.out.println(c.getC_name());
			System.out.println(c.getC_startdate());
			System.out.println(c.getC_numberofparticipants());
		}
		
		
//		LocalDateTime now = LocalDateTime.now();
//		System.out.println(now);
//		List<PlanDTO> plan= ReadDailyPlan("sanghee");
//		for (PlanDTO p: plan) {
//			System.out.println(p.getP_id());
//			System.out.println(p.getP_title());
//			System.out.println(p.getP_content());
		}
//	@Autowired
//	PlanRepository repo;
//	public List<PlanDTO>ReadDailyPlan(String u_id) {
//		
//		LocalDateTime todaystime = LocalDateTime.now();
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		String realtodaystime = todaystime.format(formatter);
//		
//		List<Plan>planList = repo.findByUidAndPStartDateOrderByPStartTimeAsc(u_id, realtodaystime);
//		List<PlanDTO>userDailyPlanList = new ArrayList<>();
//		for(Plan p: planList) {
//			userDailyPlanList.add(
//					PlanDTO.builder()
//						.p_id(p.getP_id())
//						.u_id(p.getU_id())
//						.p_startdate(p.getP_startdate())
//						.p_enddate(p.getP_enddate())
//						.p_starttime(p.getP_starttime())
//						.p_endtime(p.getP_endtime())
//						.p_title(p.getP_title())
//						.p_content(p.getP_content())
//						.p_category(p.getP_category())
//						.p_remindornot(p.getP_remindornot())
//						.build()
//					);
//		}
//		return userDailyPlanList;
//
//	}
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
	}
	public List<ChallengeDTO>ReadRecentChallenge(){
		List<Challenge>recentListEntity = challengeRepository.findAllByOrderByCnumberofparticipantsDesc();
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
