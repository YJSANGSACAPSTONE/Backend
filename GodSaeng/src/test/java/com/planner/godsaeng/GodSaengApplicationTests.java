package com.planner.godsaeng;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.planner.godsaeng.dto.PlanDTO;
import com.planner.godsaeng.entity.Plan;
import com.planner.godsaeng.repository.PlanRepository;


@SpringBootTest
class GodsaengApplicationTests {
	PlanDTO plan = null;
	

	
	@Test
	void contextLoads() {
		
		LocalDateTime now = LocalDateTime.now();
		System.out.println(now);
		List<PlanDTO> plan= ReadDailyPlan("sanghee");
		for (PlanDTO p: plan) {
			System.out.println(p.getP_id());
			System.out.println(p.getP_title());
			System.out.println(p.getP_content());
		}
		
		
		
	
	}
	@Autowired
	PlanRepository repo;
	public List<PlanDTO>ReadDailyPlan(String u_id) {
		
		LocalDateTime todaystime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String realtodaystime = todaystime.format(formatter);
		
		List<Plan>planList = repo.findByUidAndPStartDateOrderByPStartTimeAsc(u_id, realtodaystime);
		List<PlanDTO>userDailyPlanList = new ArrayList<>();
		for(Plan p: planList) {
			userDailyPlanList.add(
					PlanDTO.builder()
						.p_id(p.getP_id())
						.u_id(p.getU_id())
						.p_startdate(p.getP_startdate())
						.p_enddate(p.getP_enddate())
						.p_starttime(p.getP_starttime())
						.p_endtime(p.getP_endtime())
						.p_title(p.getP_title())
						.p_content(p.getP_content())
						.p_category(p.getP_category())
						.p_remindornot(p.getP_remindornot())
						.build()
					);
		}
		return userDailyPlanList;

	}
}
