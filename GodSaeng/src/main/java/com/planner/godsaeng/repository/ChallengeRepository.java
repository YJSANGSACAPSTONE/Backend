package com.planner.godsaeng.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.planner.godsaeng.entity.Challenge;

public interface ChallengeRepository extends JpaRepository<Challenge,Long > {
	
	List<Challenge>findAllByOrderByCnumberofparticipantsDesc();
	List<Challenge>findAllByOrderByCstartdateDesc();
	
	@Query(value = "select * from godsaeng_challenge where u_id=:u_id", nativeQuery = true)
	List<Challenge>findByUid(@Param("u_id")String uid);
}
