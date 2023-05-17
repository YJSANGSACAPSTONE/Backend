package com.planner.godsaeng.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planner.godsaeng.entity.PostImage;

public interface FileRepository extends JpaRepository<PostImage, String> {

}
