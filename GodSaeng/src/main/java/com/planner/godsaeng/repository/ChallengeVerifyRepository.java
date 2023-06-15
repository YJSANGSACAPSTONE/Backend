package com.planner.godsaeng.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.planner.godsaeng.entity.ChallengeVerify;

public interface ChallengeVerifyRepository extends JpaRepository<ChallengeVerify, Long> {
	
//	@Query("SELECT cv FROM ChallengeVerify cv WHERE cv.cid = :cid")
//	List<ChallengeVerify> findByCid(@Param("cid") Long cid);
	@Query(value = "SELECT * FROM godsaeng_challengeverify WHERE cid = :cid", nativeQuery = true)
	List<ChallengeVerify> findByCid(@Param("cid") Long cid);	

}
