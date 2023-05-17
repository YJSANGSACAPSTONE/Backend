package com.planner.godsaeng.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.planner.godsaeng.dto.ZepIdVerifyViewDTO;
import com.planner.godsaeng.entity.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, String> {

	@Query(value = "SELECT uid FROM Godsaeng_user WHERE uzepid = :uzepid", nativeQuery = true)
	String findUidByUzepid(@Param("uzepid") String uzepid);
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE Godsaeng_user SET udeposit = udeposit - :participatefee WHERE uid = :uid", nativeQuery = true)
	void updateDeposit(@Param("uid") String uid, @Param("participatefee") Integer participatefee);
	
	@Modifying
	@Query(value = "UPDATE Godsaeng_user SET udeposit = udeposit + :newDeposit WHERE uid = :uid", nativeQuery = true)
	void addDeposit(@Param("uid") String uid, @Param("newDeposit") Integer newDeposit);
	
	Optional<User> findByUid(String uid);
	
	@Query(value= "SELECT uzepid FROM Godsaeng_user Where uid= :uid", nativeQuery = true)
	String findUzepidByUid(@Param("uid") String uid);

	@Modifying
	@Query(value = "UPDATE Godsaeng_user u SET u.uzepid = :newZepid, u.uverifiedornot = 1 WHERE u.uid = :uid", nativeQuery = true)
	void updateZepid(@Param("uid") String uid, @Param("newZepid") String newZepid);
	
	@Query(value = "SELECT uverifiedornot, uzepid FROM Godsaeng_user WHERE uid = ?1", nativeQuery = true)
	User findUzepidAndUverifiedornotByUid(@Param("uid") String uid);
	
//	@Query(value = "SELECT uid FROM godsaeng_user", nativeQuery = true)
//    Optional<User> findByUid(String uid);

}