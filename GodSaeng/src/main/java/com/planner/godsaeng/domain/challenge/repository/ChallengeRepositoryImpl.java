package com.planner.godsaeng.domain.challenge.repository;

import com.planner.godsaeng.entity.Challenge;
import com.planner.godsaeng.entity.ChallengeParticipate;
import com.planner.godsaeng.entity.QChallenge;
import com.planner.godsaeng.entity.QChallengeParticipate;
import com.planner.godsaeng.entity.QChallengeVerify;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ChallengeRepositoryImpl implements ChallengeRepositoryCustom {
	
	private final JPAQueryFactory queryFactory;
	
//	public List<Challenge>findChallengeByUid(String uid){
//		return queryFactory
//				.select(challenge)
//				.from(challenge)
//				.join(challengeParticipate)
//				.on(challenge.cid.eq(ChallengeParticipate.cid))
//				.where(challengeParticipate.uid.eq(uid)
//						.and(challengeParticipate.cpfinalsuccess.eq(0)))
//				.fetch();
//	}
	
//	//Inner Join을 사용하여 challengeparticipate에서 어떤 챌린지에 참가중인 uid를대조한다.
//	//그 uid에 대조된 cid를 활용하여 현재 유저(세션에 담긴 uid)가 참가중인 challenge 정보를 challenge 테이블에서 가져온다.
//	//이때 AND문을 사용하여 cpfinalsuccess(챌린지 최종 성공여부)가 0인 현재 진행중인 챌린지 정보를 가져온다!
//	@Query(value = "SELECT * FROM godsaeng_challenge c JOIN godsaeng_challengeparticipate cp ON c.cid = cp.cid WHERE cp.uid = :uid AND cp.cpfinalsuccess = 0", nativeQuery = true)
//	List<Challenge>findChallengeByUid(@Param("uid")String uid);
//		
//	//성공한 챌린지 목록 가져오기.
//	@Query(value = "SELECT * FROM godsaeng_challenge c JOIN godsaeng_challengeparticipate cp ON c.cid = cp.cid WHERE cp.uid = :uid AND cp.cpfinalsuccess = 1", nativeQuery = true)
//	List<Challenge>findFinishedChallengeByUid(@Param("uid")String uid);
//		
//	@Query(value = "select * from godsaeng_challenge where uid=:uid", nativeQuery = true)
//	List<Challenge>findByUid(@Param("uid")String uid);
	
	
	

}
