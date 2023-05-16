package com.planner.godsaeng.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.planner.godsaeng.entity.Payment;

@Repository
public interface KakaoPayRepository extends JpaRepository<Payment, Long> {
//	Optional<User> findByUser(User user);
	
//	Optional<Payment> findByUid(String uid);
	
	@Query(value = "SELECT * FROM godsaeng_payment WHERE uid = :uid", nativeQuery = true)
	List<Payment> findPaymentByUid(@Param("uid") String uid);
	
//	List<Payment> findByUserUid(String userUid);
}
