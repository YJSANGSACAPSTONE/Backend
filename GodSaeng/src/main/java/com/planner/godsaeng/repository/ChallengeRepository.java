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
	
	
	//Inner Join을 사용하여 challengeparticipate에서 어떤 챌린지에 참가중인 uid를대조한다.
	//그 uid에 대조된 cid를 활용하여 현재 유저(세션에 담긴 uid)가 참가중인 challenge 정보를 challenge 테이블에서 가져온다.
	//이때 AND문을 사용하여 cpfinalsuccess(챌린지 최종 성공여부)가 0인 현재 진행중인 챌린지 정보를 가져온다!
	@Query(value = "SELECT * FROM godsaeng_challenge c JOIN godsaeng_challengeparticipate cp ON c.cid = cp.cid WHERE cp.u_id = :u_id AND cp.cpfinalsuccess = 0", nativeQuery = true)
	List<Challenge>findChallengeByUid(@Param("u_id")String uid);
	
	//성공한 챌린지 목록 가져오기.
	@Query(value = "SELECT * FROM godsaeng_challenge c JOIN godsaeng_challengeparticipate cp ON c.cid = cp.cid WHERE cp.u_id = :u_id AND cp.cpfinalsuccess = 1", nativeQuery = true)
	List<Challenge>findFinishedChallengeByUid(@Param("u_id")String uid);
	
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
