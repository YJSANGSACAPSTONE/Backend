package com.planner.godsaeng.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planner.godsaeng.dto.PlanDTO;
import com.planner.godsaeng.entity.Plan;
import com.planner.godsaeng.entity.User;
import com.planner.godsaeng.repository.PlanRepository;
import com.planner.godsaeng.repository.UserRepository;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanService {	
	private final PlanRepository planRepository;
	private final UserRepository userRepository;
	
	
	Plan plan = null;
	
	//플랜 CREATE(INSERT)
	public boolean InsertPlan(PlanDTO d) {
		//dto to entity
		String uid = "sanghee_ok@naver.com";
		Optional<User> user = userRepository.findById(d.getU_id());
		
		plan = Plan.builder()
				.user(user.get())
				.pstartdate(d.getP_startdate())
				.penddate(d.getP_enddate())
				.pstarttime(d.getP_starttime())
				.pendtime(d.getP_endtime())
				.ptitle(d.getP_title())
				.pcontent(d.getP_content())
				.pcategory(d.getP_category())
				.premindornot(d.getP_remindornot())
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
			//entity to dto
			for(Plan p: planList) {
				userDailyPlanList.add(
						PlanDTO.builder()
							.p_id(p.getPid())
							.p_startdate(p.getPstartdate())
							.p_enddate(p.getPenddate())
							.p_starttime(p.getPstarttime())
							.p_endtime(p.getPendtime())
							.p_title(p.getPtitle())
							.p_content(p.getPcontent())
							.p_category(p.getPcategory())
							.p_remindornot(p.getPremindornot())
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
		Optional<Plan>result = planRepository.findById(d.getP_id());
		String uid = "sanghee_ok@naver.com";
		Optional<User> user = userRepository.findById(d.getU_id());
		if(result.isPresent()) {
			plan = Plan.builder()
					.pid(d.getP_id())
					.user(user.get())
					.pstartdate(d.getP_startdate())
					.penddate(d.getP_enddate())
					.pstarttime(d.getP_starttime())
					.pendtime(d.getP_endtime())
					.ptitle(d.getP_title())
					.pcontent(d.getP_content())
					.pcategory(d.getP_category())
					.premindornot(d.getP_remindornot())
					.build();
		}
			
		try {
			planRepository.save(plan);
			return true;
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}	
	}
	
	
	public boolean DeletePlan(Long p_id) {
		try {
			planRepository.deleteById(p_id);
			return true;
			}catch(Exception e) {
				e.printStackTrace();
				return false;
			}
	}
	

}
