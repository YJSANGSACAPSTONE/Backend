package com.planner.godsaeng.repository;

import java.util.Optional;

import javax.transaction.Transactional;

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
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE Godsaeng_user SET udeposit = udeposit - :participatefee WHERE uid = :uid", nativeQuery = true)
	void updateDeposit(@Param("uid") String uid, @Param("participatefee") Integer participatefee);
	
	Optional<User> findByUid(String uid);

//	@Query(value = "SELECT uid FROM godsaeng_user", nativeQuery = true)
//    Optional<User> findByUid(String uid);

}