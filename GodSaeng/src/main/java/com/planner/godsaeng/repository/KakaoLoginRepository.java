package com.planner.godsaeng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.planner.godsaeng.entity.User;

@Repository
public interface KakaoLoginRepository extends JpaRepository<User, String>{

}
