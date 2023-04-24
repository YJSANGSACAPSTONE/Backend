package com.planner.godsaeng.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planner.godsaeng.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
