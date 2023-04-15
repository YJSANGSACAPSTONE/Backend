package com.planner.godsaeng.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.planner.godsaeng.dto.PlanDTO;
import com.planner.godsaeng.entity.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
	//시작일과 종료일을 기준으로 현재 시간과 대조하여 그 사이에 있는 레코드들을 출력해주는 메서드
	//List<Plan>findByStartDateLessThanEqualAndEndDateGreaterThanEqual(LocalDateTime p_startdate, LocalDateTime p_enddate);
	
	//List<Plan>findByUidAndPStartDateOrderByPStartTimeAsc(String u_id,LocalDateTime p_startdate);
	
	@Query(value = "SELECT * FROM godsaeng_plan p WHERE p.u_id = :u_id AND DATE(p.p_startdate) = :realtodaystime ORDER BY p.p_starttime ASC", nativeQuery = true)
	List<Plan> findByUidAndPStartDateOrderByPStartTimeAsc(@Param("u_id") String u_id, @Param("realtodaystime") String realtodaystime);
	
	
	/* JPQL로 해결되지 않아서 그냥 NATIVE QUERY로 처리함 !
	 * @Transactional
	 * 
	 * @Query("SELECT p FROM godsaeng_plan p WHERE (p.u_id = :u_id AND DATE(p.p_startdate) = :realtodaystime) ORDER BY p.p_starttime ASC"
	 * ) List<Plan> findByUidAndPStartDateOrderByPStartTimeAsc(@Param("u_id") String
	 * u_id, @Param("realtodaystime") String realtodaystime);
	 */
	
}


