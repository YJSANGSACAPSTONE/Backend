package com.planner.godsaeng.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.planner.godsaeng.entity.UsageHistory;

@Repository
public interface UsageHistoryRepository extends JpaRepository<UsageHistory, Long>{

	@Query(value = "SELECT * FROM godsaeng_usagehistory WHERE uid = :uid", nativeQuery = true)
	List<UsageHistory> findUsageHistoryByUid(@Param("uid") String uid);
}
