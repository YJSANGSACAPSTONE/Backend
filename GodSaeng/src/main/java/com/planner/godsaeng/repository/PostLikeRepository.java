package com.planner.godsaeng.repository;

import com.planner.godsaeng.entity.Post;
import com.planner.godsaeng.entity.PostLike;
import com.planner.godsaeng.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
	Optional<PostLike> findByPostAndUser(Post post, User user);
}