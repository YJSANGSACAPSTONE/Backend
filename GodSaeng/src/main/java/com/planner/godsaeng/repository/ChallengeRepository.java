package com.planner.godsaeng.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.planner.godsaeng.entity.Challenge;

public interface ChallengeRepository extends JpaRepository<Challenge,Long > {
	
	//참가자순으로 챌린지 정렬(인기챌린지 view)
	List<Challenge>findAllByOrderByCnumberofparticipantsDesc();
	//최신순으로 정렬(최신챌린지 view)
	List<Challenge>findAllByOrderByCstartdateDesc();
	
	@Query(value = "select * from godsaeng_challenge where u_id=:u_id", nativeQuery = true)
	List<Challenge>findByUid(@Param("u_id")String uid);
//	List<Challenge>resultList(@Param("keyword")String keyword) = entityManager.createQuery("select * from challenge where ctitle like concat('%',:searchKeyword,'%')",Challenge.class)
//			.setParameter("searchKeyword",searchKeyword)
//			.getResultList();
	//***챌린지 검색 매서드**//
	//챌린지 제목으로 검색
	List<Challenge> findByCnameContaining(String keyword);
	//챌린지 내용으로 검색
	List<Challenge> findByCcontentContaining(String keyword);
	//챌린지 참가 시작일별로 검색
	List<Challenge> findByCstartdateContaining(String keyword);
	//챌린지 카테고리별로 검색
	List<Challenge> findByCcategoryContaining(String keyword);
	
	
	
//	boolean existsBySearchWord(String searchword);
//	List<Challenge>findAllByOrderBySearchWordCntDesc();
//	Challenge findBySearchword(String searchWord);
}
