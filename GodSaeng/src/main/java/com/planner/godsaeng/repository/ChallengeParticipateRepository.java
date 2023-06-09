package com.planner.godsaeng.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.planner.godsaeng.entity.ChallengeParticipate;
import com.planner.godsaeng.entity.ChallengeParticipateId;

public interface ChallengeParticipateRepository extends JpaRepository<ChallengeParticipate, ChallengeParticipateId> {
	
	
	//cid와 uid를 통하여 cpid를 역산해내는 쿼리문
	
//	@Query(value = "SELECT cpid FROM Godsaeng_Challengeparticipate WHERE cid = :cid AND uid = :uid", nativeQuery = true)
//	Long findCpidByCidAndUid(@Param("cid") Long cid, @Param("uid") String uid);
//	
//	Optional<ChallengeParticipate> findByCidAndUid(Long cid, String uid);
	
}
	

