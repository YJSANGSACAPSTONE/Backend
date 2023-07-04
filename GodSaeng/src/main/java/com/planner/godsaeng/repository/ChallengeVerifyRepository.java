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
	
	
	
	@Query(value = "SELECT uid, COUNT(*)AS cvCount, cvtime FROM godsaeng_challengeverify WHERE cid = :cid AND cvsuccessornot = 1 AND DATE(cvtime) = CURDATE() GROUP BY uid ORDER BY cvtime asc", nativeQuery = true)
	List<Object[]>getChallengeRankData(@Param("cid")Long cid);
	
	@Query(value = "SELECT u.unickname , COUNT(*)AS cvCount, cv.uid FROM godsaeng_challengeverify cv JOIN godsaeng_user u ON cv.uid = u.uid WHERE cv.cid = :cid GROUP BY u.unickname, cv.uid ORDER BY cvCount desc", nativeQuery = true)
	List<Object[]>getCodingChallengeRankData(@Param("cid")Long cid);

	
	    
	
}
