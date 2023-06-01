package com.planner.godsaeng.repository;

import java.util.List;

import com.planner.godsaeng.entity.Plan;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class PlanRepositoryImpl implements PlanRepositoryCustom {
    private final JPAQueryFactory queryFactory;


	@Override
	public List<Plan> findByUidAndPStartDateOrderByPStartTimeAsc(String uid, String realtodaystime) {
		
	}

	@Override
	public List<Plan> findByUidAndPStartDateOrderByPStartTimeAscc(String uid, String realtodaystime) {
		
	}

}
