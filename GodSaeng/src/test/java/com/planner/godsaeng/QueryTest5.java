package com.planner.godsaeng;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.planner.godsaeng.dto.PlanDTO;
import com.planner.godsaeng.entity.Plan;
import com.planner.godsaeng.repository.PlanRepositoryImpl;
import com.planner.godsaeng.service.PlanService;

public class QueryTest5 {
	
	@Autowired
	PlanRepositoryImpl repository;
	@Autowired
	PlanService service;
	
	@Test
	void Contextloads() {
		List<PlanDTO>dto = new ArrayList<PlanDTO>();
		List<Plan> plan = repository.findByUidAndPStartDateOrderByPStartTimeAsc("sanghee_ok@naver.com", "2023-06-01");
		
		
			}

}
