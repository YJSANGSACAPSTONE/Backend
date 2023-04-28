package com.planner.godsaeng.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.planner.godsaeng.entity.Post;
import com.planner.godsaeng.repository.search.SearchPostRepository;

public interface PostRepository extends JpaRepository<Post, Long>, SearchPostRepository {
//				QuerydslPredicateExecutor<Post>, 
	
	@Query("DELETE FROM Post p WHERE p.pid = :pid")
	void deleteByBid(Long pid);
	
	@Query("SELECT p, u FROM Post p JOIN User u ON p.uid = u.uid AND p.pid = :pid")
	Object getPostWithUid(@Param("pid") Long pid);
	
//	@Query("SELECT b, p FROM Board b JOIN b.posts p WHERE p.pid = :pid AND b.bid = :bid")
//	Object getPostWithBid(@Param("bid") Long pid);
	
//	@Query("SELECT b, r FROM Board b LEFT JOIN Reply r ON r.board = b WHERE b.bno = :bno")
//	List<Object[]> getBoardWithReply(@Param("bno") Long bno);
//	
//	@Query(value = "SELECT b, w, count(r) " +
//				   " FROM Board b " +
//				   " LEFT JOIN b.writer w " +
//				   " LEFT JOIN Reply r ON r.board = b" +	
//				   " GROUP BY b ",
//				   countQuery = "SELECT count(b) FROM Board b")
//	Page<Object[]> getBoardWithReplyCount(Pageable pageable);
//	
//	@Query("SELECT p, u, b " +
//		   " FROM Post p LEFT JOIN p.uid u " +
//		   " LEFT JOIN Board b ON b.post = b " +
//		   " WHERE p.pid = :pid")
//	Object getPostByPid(@Param("pid") Long pid);

}

  
  