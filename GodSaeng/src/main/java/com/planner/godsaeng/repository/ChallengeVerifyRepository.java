package com.planner.godsaeng.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.planner.godsaeng.entity.ChallengeVerify;

public interface ChallengeVerifyRepository extends JpaRepository<ChallengeVerify, Long> {
	
//	@Query("SELECT cv FROM ChallengeVerify cv WHERE cv.cid = :cid")
//	List<ChallengeVerify> findByCid(@Param("cid") Long cid);
	@Query(value = "SELECT * FROM godsaeng_challengeverify WHERE cid = :cid AND cvsuccessornot = 0", nativeQuery = true)
	List<ChallengeVerify> findByCid(@Param("cid") Long cid);
	
	

	@Query("SELECT COUNT(cv.cvid) " +
		       "FROM ChallengeVerify cv " +
		       "WHERE cv.cvtime >= :startDate AND cv.cvtime < :endDate")
	Long countVerifyByDateRange(LocalDateTime startDate, LocalDateTime endDate);
	
	@Query("SELECT cv.cvtime, COUNT(cvid) " +
	            "FROM ChallengeVerify cv " +
	            "WHERE cv.cvtime >= :startDate " +
	            "GROUP BY cv.cvtime " +
	            "ORDER BY cv.cvtime DESC")
	List<Object[]> countVerifyByRecentDays(LocalDateTime startDate);
	
	@Query(value = "SELECT c.cid, c.cname, c.cnumberofparticipants, COUNT(DISTINCT cp.uid)AS cpcount,"
			+" COUNT(cv.cid)AS cvcount FROM godsaeng_challenge c, godsaeng_challengeparticipate cp, godsaeng_challengeverify cv"
			+ " WHERE cp.cid = c.cid"
			+ " AND cv.cid = cp.cid"
			+ " GROUP BY cid;", nativeQuery = true)
	List<Object[]> getChallengeStatistics();

	
	    
	
}
