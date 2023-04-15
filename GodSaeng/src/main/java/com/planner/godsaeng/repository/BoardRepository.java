package com.planner.godsaeng.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planner.godsaeng.entity.Board;

public interface BoardRepository extends JpaRepository<Board, String> {

}
