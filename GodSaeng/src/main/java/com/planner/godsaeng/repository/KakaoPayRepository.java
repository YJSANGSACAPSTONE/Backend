package com.planner.godsaeng.repository;

import java.util.Optional;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.planner.godsaeng.entity.Payment;

@Repository
public interface KakaoPayRepository extends JpaRepository<Payment, Long> {
//	Optional<User> findByUser(User user);
//	
//	Optional<Payment> findByUid(String uid);
}
