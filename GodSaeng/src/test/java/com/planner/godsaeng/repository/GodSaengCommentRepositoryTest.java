package com.planner.godsaeng.repository;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.planner.godsaeng.entity.Comment;
import com.planner.godsaeng.entity.Post;
import com.planner.godsaeng.entity.User;

@SpringBootTest
public class GodSaengCommentRepositoryTest {
	
	@Autowired
	private CommentRepository commentRepository;
	
//	@Test
//	public void insertPostComments() {
//		
//		// 200개 댓글 등록
//		IntStream.rangeClosed(1, 100).forEach(i -> {
//			// 게시물 번호
//			Long poid = ((long)(Math.random()*100) + 1);
//			
//			// 댓글러 번호
//			String uid = ("userID" + i);
//			
//			User user = User.builder().uid(uid).build();
//			
//			Comment postComment = Comment.builder()
//					.user(user)
//					.post(Post.builder().poid(poid).build())
//					.text("이 게시글은 감명 깊다..." + i)
//					.build();
//			
//			commentRepository.save(postComment);
//		});
//	}
	
//	@Test
//    public void testGetMovieReviews(){
//
//        Post post = Post.builder().poid(58L).build();
//
//        List<Comment> result = commentRepository.findByPost(post);
//
//        result.forEach(postComment -> {
//            System.out.println(postComment.getCommentId());
//            System.out.println("\t" + postComment.getText());
//            System.out.println("\t" + postComment.getUser().getUid());
//            System.out.println("----------------------------");
//        });
//    }
}
