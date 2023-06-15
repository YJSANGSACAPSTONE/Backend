package com.planner.godsaeng.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.planner.godsaeng.dto.ChallengeStatusDTO;
import com.planner.godsaeng.entity.Challenge;
import com.querydsl.jpa.impl.JPAQueryFactory;

public interface ChallengeRepository extends JpaRepository<Challenge,Long > {
		
	//인풋받아서 그 값을 기반으로 cid 찾기
	List<Challenge> findByCid(Long cid);
	
	//참가자순으로 챌린지 정렬(인기챌린지 view)
	List<Challenge>findAllByOrderByCnumberofparticipantsDesc();
	//최신순으로 정렬(최신챌린지 view)
	List<Challenge>findAllByOrderByCstartdateDesc();
	//참가금순으로 정렬(챌린지랭킹 view)
	@Query(value = "SELECT * FROM godsaeng_challenge ORDER BY cnumberofparticipants*cfee DESC LIMIT 10",nativeQuery=true)
	List<Challenge>findTop10ChallengesByFeeDesc();
	
	//Inner Join을 사용하여 challengeparticipate에서 어떤 챌린지에 참가중인 uid를대조한다.
	//그 uid에 대조된 cid를 활용하여 현재 유저(세션에 담긴 uid)가 참가중인 challenge 정보를 challenge 테이블에서 가져온다.
	//이때 AND문을 사용하여 cpfinalsuccess(챌린지 최종 성공여부)가 0인 현재 진행중인 챌린지 정보를 가져온다!
	@Query(value = "SELECT * FROM godsaeng_challenge c JOIN godsaeng_challengeparticipate cp ON c.cid = cp.cid WHERE cp.uid = :uid AND cp.cpfinalsuccess = 0", nativeQuery = true)
	List<Challenge>findChallengeByUid(@Param("uid")String uid);
	
	//성공한 챌린지 목록 가져오기.
	@Query(value = "SELECT * FROM godsaeng_challenge c JOIN godsaeng_challengeparticipate cp ON c.cid = cp.cid WHERE cp.uid = :uid AND cp.cpfinalsuccess = 1", nativeQuery = true)
	List<Challenge>findFinishedChallengeByUid(@Param("uid")String uid);
	
	@Query(value = "select * from godsaeng_challenge where uid=:uid", nativeQuery = true)
	List<Challenge>findByUid(@Param("uid")String uid);
//	List<Challenge>resultList(@Param("keyword")String keyword) = entityManager.createQuery("select * from challenge where ctitle like concat('%',:searchKeyword,'%')",Challenge.class)
//			.setParameter("searchKeyword",searchKeyword)
//			.getResultList();
	//***챌린지 검색 매서드**//
	//챌린지 제목으로 검색
	@Query("SELECT g FROM Challenge g WHERE g.cstartdate <= CURRENT_DATE AND g.cenddate >= CURRENT_DATE")
	List<Challenge>findActiveChallenges();
	
	List<Challenge> findByCnameContaining(String keyword);
	//챌린지 내용으로 검색
	List<Challenge> findByCcontentContaining(String keyword);
	//챌린지 참가 시작일별로 검색
	List<Challenge> findByCstartdateContaining(String keyword);
	//챌린지 카테고리별로 검색
	List<Challenge> findByCcategoryContaining(String keyword);
	
//	@Query(value = "SELECT godsaeng_challenge.cid, cname, cthumbnails, cstartdate, cenddate, " +
//	        "cenddate - cstartdate + 1 AS datediff, " +
//	        "CASE " +
//	        "    WHEN ctypeoffrequency = 2 THEN CEILING((cenddate - cstartdate + 1) / cfrequency) " +
//	        "    WHEN ctypeoffrequency = 1 THEN (cenddate - cstartdate + 1) * cfrequency " +
//	        "END AS totalcount, " +
//	        "godsaeng_challengeparticipate.cpid, " +
//	        "COUNT(godsaeng_challengeverify.cvsuccessornot) AS cvsuccesscount " +
//	        "FROM godsaeng_challenge " +
//	        "JOIN godsaeng_challengeparticipate " +
//	        "    ON godsaeng_challenge.cid = godsaeng_challengeparticipate.cid " +
//	        "LEFT JOIN godsaeng_challengeverify " +
//	        "    ON godsaeng_challengeparticipate.cpid = godsaeng_challengeverify.cpid " +
//	        "    AND godsaeng_challengeverify.cvsuccessornot = 1 " +
//	        "WHERE godsaeng_challengeparticipate.uid = ?1 " +
//	        "    AND godsaeng_challengeparticipate.cpfinalsuccess = 0 " +
//	        "GROUP BY godsaeng_challengeparticipate.cpid", nativeQuery = true)
//	List<Object[]> myChallengeProgress(@Param("uid") String uid);
//	
	@Query(value = "SELECT godsaeng_challenge.cid AS challenge_cid, cname, cthumbnails, cstartdate, cenddate, " +
	        "cenddate - cstartdate + 1 AS datediff, " +
	        "CASE " +
	        "    WHEN ctypeoffrequency = 2 THEN CEILING((cenddate - cstartdate + 1) / cfrequency) " +
	        "    WHEN ctypeoffrequency = 1 THEN (cenddate - cstartdate + 1) * cfrequency " +
	        "END AS totalcount, " +
	        "godsaeng_challengeparticipate.cid AS cid, godsaeng_challengeparticipate.uid AS uid, " +
	        "COUNT(godsaeng_challengeverify.cvsuccessornot=1) AS cvsuccesscount " +
	        "FROM godsaeng_challenge " +
	        "JOIN godsaeng_challengeparticipate " +
	        "    ON godsaeng_challenge.cid = godsaeng_challengeparticipate.cid " +
	        "LEFT JOIN godsaeng_challengeverify " +
	        "    ON godsaeng_challengeparticipate.cid = godsaeng_challengeverify.cid " +
	        "    AND godsaeng_challengeparticipate.uid = godsaeng_challengeverify.uid " +
	        "    AND godsaeng_challengeverify.cvsuccessornot = 1 " +
	        "WHERE godsaeng_challengeparticipate.uid = ?1 " +
	        "    AND godsaeng_challengeparticipate.cpfinalsuccess = 0 " +
	        "GROUP BY godsaeng_challengeparticipate.cid, godsaeng_challengeparticipate.uid", nativeQuery = true)
	List<Object[]> myChallengeProgress(@Param("uid") String uid);

	
	
//	boolean existsBySearchWord(String searchword);
//	List<Challenge>findAllByOrderBySearchWordCntDesc();
//	Challenge findBySearchword(String searchWord);
}
