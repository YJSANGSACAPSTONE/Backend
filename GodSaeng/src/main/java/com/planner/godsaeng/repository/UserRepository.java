package com.planner.godsaeng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.planner.godsaeng.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	@Query(value = "SELECT uid FROM Godsaeng_user WHERE uzepid = :uzepid", nativeQuery = true)
	   String findUidByUzepid(@Param("uzepid") String uzepid);
	
	@Modifying
	@Query(value = "UPDATE Godsaeng_user SET deposit = deposit - :newDeposit WHERE uid = :uid", nativeQuery = true)
	void updateDeposit(@Param("uid") String uid, @Param("newDeposit") Integer newDeposit);
	
//	@Query(value = "SELECT uid FROM godsaeng_user", nativeQuery = true)
//    Optional<User> findByUid(String uid);
}