package com.planner.godsaeng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.planner.godsaeng.entity.Payment;

@Repository
public interface KakaoPayRepository extends JpaRepository<Payment, Long> {

}
