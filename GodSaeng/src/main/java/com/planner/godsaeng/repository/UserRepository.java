package com.planner.godsaeng.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planner.godsaeng.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}