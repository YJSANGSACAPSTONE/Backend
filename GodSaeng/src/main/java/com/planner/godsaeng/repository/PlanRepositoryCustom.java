package com.planner.godsaeng.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.planner.godsaeng.entity.Plan;

public interface PlanRepositoryCustom {
	List<Plan> findByUidAndPStartDateOrderByPStartTimeAsc(@Param("u_id") String uid, @Param("realtodaystime") String realtodaystime);
	List<Plan> findByUidAndPStartDateOrderByPStartTimeAscc(@Param("u_id") String uid, @Param("realtodaystime") String realtodaystime);


}
