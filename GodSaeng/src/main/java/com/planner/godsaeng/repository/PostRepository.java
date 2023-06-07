package com.planner.godsaeng.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.planner.godsaeng.entity.Post;
import com.planner.godsaeng.repository.SearchPostRepository;

public interface PostRepository extends JpaRepository<Post, Long>, QuerydslPredicateExecutor<Post>, SearchPostRepository {
	
	Optional<Post> findByPoid(Long poid);
	
	// 인기글 검색
    @Query("SELECT p.post FROM PostLike p GROUP BY p.post ORDER BY COUNT(p.post) DESC")
    List<Post> getPopularPosts(int limit);
	
	@Query("SELECT p, u"
			+ " FROM Post p JOIN User u ON p.user = u AND p.poid = :poid")
	Object getPostWithUid(@Param("poid") Long poid);
	
	//페이지 처리
	@Query("select p, pi, count(distinct c) from Post p " +
            "left outer join PostImage pi on pi.post = p " +
            "left outer join Comment c on c.post = p group by p ")
    Page<Object[]> getListPage(Pageable pageable);
    
    // bid에 대한 post리스트 가져옴
    @Query("SELECT p, pi, COUNT(DISTINCT c) FROM Post p " +
    	       "LEFT OUTER JOIN PostImage pi ON pi.post = p " +
    	       "LEFT OUTER JOIN Comment c ON c.post = p " +
    	       "WHERE p.board.bid = :bid " +
    	       "GROUP BY p")
    Page<Object[]> getListPageByBoardId(@Param("bid") int bid, Pageable pageable);

    
    // 특정 게시물 조회
    @Query("SELECT p, pi, u, count(distinct c), count(distinct pl)"
            + " FROM Post p LEFT OUTER JOIN PostImage pi ON pi.post = p"
            + " JOIN User u ON p.user = u AND p.poid = :poid"
            + " LEFT OUTER JOIN Comment c ON c.post = p"
            + " LEFT OUTER JOIN PostLike pl ON pl.post = p"
            + " WHERE p.poid = :poid"
            + " GROUP BY pi")
    List<Object[]> getPostWithAll(@Param("poid") Long poid);

}

  
  