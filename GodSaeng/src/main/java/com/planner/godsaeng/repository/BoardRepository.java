package com.planner.godsaeng.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.planner.godsaeng.entity.Board;
import com.planner.godsaeng.entity.Post;

public interface BoardRepository extends JpaRepository<Board, String> {
	
	// bid에 따른 해당 post들을 가져오는 쿼리
    @Query("SELECT p FROM Post p WHERE p.board.bid = :bid")
    List<Post> findPostsByBoardId(@Param("bid") int bid);
    
}
