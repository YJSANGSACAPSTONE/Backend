package com.planner.godsaeng.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planner.godsaeng.entity.Challenge;

public interface ChallengeRepository extends JpaRepository<Challenge,Long > {

}
