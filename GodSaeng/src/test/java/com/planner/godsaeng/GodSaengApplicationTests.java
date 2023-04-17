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
import com.planner.godsaeng.repository.PlanRepository;


@SpringBootTest
class GodsaengApplicationTests {
	PlanDTO plan = null;
	

	
	@Test
	void contextLoads() {
		
//		LocalDateTime now = LocalDateTime.now();
//		System.out.println(now);
//		List<PlanDTO> plan= ReadDailyPlan("sanghee");
//		for (PlanDTO p: plan) {
//			System.out.println(p.getP_id());
//			System.out.println(p.getP_title());
//			System.out.println(p.getP_content());
		}
		
		
		
	
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
//	@Autowired
//	ChallengeRepository challengeRepository;
//	
//	public List<ChallengeDTO>ReadPopularChallenge(){
//		List<Challenge>popularListEntity=challengeRepository.findAllByOrderByStartdateDesc();
//		List<ChallengeDTO>popularList=new ArrayList<>();
//		
//		for(Challenge e : popularListEntity) {
//			popularList.add(
//					ChallengeDTO.builder()
//					.c_id(e.getC_id())
//					.c_name(e.getC_name())
//					.c_content(e.getC_content())
//					.c_startdate(e.getC_startdate())
//					.c_enddate(e.getC_enddate())
//					.c_numberofparticipants(e.getC_numberofparticipants())
//					.c_category(e.getC_category())
//					.c_thumbnails(e.getC_thumbnails())
//					.c_introduction(e.getC_introduction())
//					.c_fee(e.getC_fee())
//					.c_numberofphoto(e.getC_numberofphoto())
//					.c_typeofverify(e.getC_typeofverify())
//					.c_typeoffrequency(e.getC_typeoffrequency())
//					.c_frequency(e.getC_frequency())
//					.c_score(e.getC_score())
//					.build()
//			);
//		}
//		return popularList;
//	}
//	
//}
