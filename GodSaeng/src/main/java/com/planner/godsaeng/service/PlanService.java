package com.planner.godsaeng.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planner.godsaeng.dto.PlanDTO;
import com.planner.godsaeng.entity.Plan;
import com.planner.godsaeng.repository.PlanRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanService {	
	@Autowired 
	private final PlanRepository planRepository;
	
	Plan plan = null;
	
	//플랜 CREATE(INSERT)
	public boolean InsertPlan(PlanDTO d) {
		plan = Plan.builder()
				.p_id(d.getP_id())
				.u_id(d.getU_id())
				.p_startdate(d.getP_startdate())
				.p_enddate(d.getP_enddate())
				.p_starttime(d.getP_starttime())
				.p_endtime(d.getP_endtime())
				.p_title(d.getP_title())
				.p_content(d.getP_content())
				.p_category(d.getP_category())
				.p_remindornot(d.getP_remindornot())
				.build();
		
		try {
		planRepository.save(plan);
		return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}	
	}
	public List<PlanDTO>ReadDailyPlan(String u_id) {
		
		LocalDateTime todaystime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String realtodaystime = todaystime.format(formatter);
		
		try {
			List<Plan>planList = planRepository.findByUidAndPStartDateOrderByPStartTimeAsc(u_id, realtodaystime);
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
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	public boolean UpdatePlan(PlanDTO d) {
		plan = Plan.builder()
				.p_id(d.getP_id())
				.u_id(d.getU_id())
				.p_startdate(d.getP_startdate())
				.p_enddate(d.getP_enddate())
				.p_starttime(d.getP_starttime())
				.p_endtime(d.getP_endtime())
				.p_title(d.getP_title())
				.p_content(d.getP_content())
				.p_category(d.getP_category())
				.p_remindornot(d.getP_remindornot())
				.build();
		try {
			planRepository.save(plan);
			return true;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}	
	}
	
	
	public boolean DeletePlan(long p_id) {
		try {
			planRepository.deleteById(p_id);
			return true;
			}catch(Exception e) {
				e.printStackTrace();
				return false;
			}
	}
	

}
