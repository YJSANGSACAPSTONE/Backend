package com.planner.godsaeng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.planner.godsaeng.entity.PostImage;

public interface PostImageRepository extends JpaRepository<PostImage, Long> {
	@Modifying
	@Query("DELETE FROM PostImage pi WHERE pi.post.poid =:poid")
	void deleteByPoid(Long poid);
}
