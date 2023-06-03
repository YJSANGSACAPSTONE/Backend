package com.planner.godsaeng.repository;

import com.planner.godsaeng.entity.Post;
import com.planner.godsaeng.entity.PostLike;
import com.planner.godsaeng.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
	
	Optional<PostLike> findByPostAndUser(Post post, User user);
	
	boolean existsByPostAndUser(Post post, User user);
	
    @Modifying //insert,update,delete 쿼리에서 벌크 연산시 사용한다
    @Query("delete from PostLike pl where pl.user = :user")
    void deleteByUser(@Param("user") User user);
    
    @Modifying
    @Query("DELETE FROM PostLike pl WHERE pl.post.poid =:poid")
    void deleteByPoid(Long poid);
    
}