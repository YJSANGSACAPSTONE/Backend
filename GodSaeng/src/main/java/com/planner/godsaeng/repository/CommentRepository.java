package com.planner.godsaeng.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.planner.godsaeng.entity.Comment;
import com.planner.godsaeng.entity.Post;
import com.planner.godsaeng.entity.User;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // @EntityGraph : 엔티티의 특정한 속성을 같이 로딩하도록 표시하는 어노테이션
    // -> 특정 기능을 수행할 때만 EAGER 로딩을 하도록 지정할 수 있다
    @EntityGraph(attributePaths = {"user"},type = EntityGraph.EntityGraphType.FETCH) // Comment 처리시 @EntityGraph 적용해 User도 같이 로딩
    List<Comment> findByPost(Post post);

    @Modifying //insert,update,delete 쿼리에서 벌크 연산시 사용한다
    @Query("delete from Comment pc where pc.user = :user")
    void deleteByUser(@Param("user") User user);
    
    @Modifying
   @Query("DELETE FROM Comment c WHERE c.post.poid =:poid")
   void deleteByPoid(Long poid);
}