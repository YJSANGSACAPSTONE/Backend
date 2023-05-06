package com.planner.godsaeng.repository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.planner.godsaeng.entity.Board;
import com.planner.godsaeng.entity.Post;
import com.planner.godsaeng.entity.PostImage;
import com.planner.godsaeng.entity.QPost;
import com.planner.godsaeng.entity.User;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@SpringBootTest
public class GodSaengPostRepositoryTest {
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private PostImageRepository imageRepository;

	// 성공
//	// 삽입 테스트
//	@Transactional
//	@Commit
//	@Test
//	public void insertPostDummies() {
//
//		IntStream.rangeClosed(1, 100).forEach(i -> {
//			User user = User.builder().uid("userID" + i).build();
//			Board board = Board.builder().bid(1).build();
//			
//			Post post = Post.builder()
//					.user(user)
//					.board(board)
//					.potitle("Title..." + (i % 5))
//					.pocontent("Content...." + (i % 8))
//					.pohitCount(i)
//					.build();
//			
//			postRepository.save(post);
//			
//			int count = (int)(Math.random() * 5) + 1;
//
//			for(int j = 0; j < count; j++) {
//				PostImage postImage = PostImage.builder()
//						.uuid(UUID.randomUUID().toString())
//						.post(post)
//						.imgName("test" + j + ".jpg").build();
//				
//				imageRepository.save(postImage);
//			}
//		});
//	}
	
	// 성공
//	// 업데이트 테스트	
//	@Test
//	public void updateTest() {
//		Optional<Post> result = postRepository.findById(5L);
//		
//		if(result.isPresent()) {
//			Post post = result.get();
//			
//			post.changeTitle("Changed Title.....");
//			post.changeContent("Changed Content.....");
//			
//			postRepository.save(post);
//		}
// 	}
	
	// 성공
//	// 제목에 특정 키워드가 있는 데이터 조회
//	@Test
//	public void testQuery1() {
//		Pageable pageable = PageRequest.of(0, 10, Sort.by("poid").descending());
//		
//		QPost qPost = QPost.post;
//		
//		String keyword = "3";
//		
//		BooleanBuilder builder = new BooleanBuilder();
//		
//		BooleanExpression expression = qPost.potitle.contains(keyword);
//		
//		builder.and(expression);
//		
//		Page<Post> result = postRepository.findAll(builder, pageable);
//		
//		result.stream().forEach(post -> {
//			System.out.println(post);
//		});
//	}
	
	// 성공
//	// 제목 또는 내용에 특정한 키워드가 있고 gno가 250보다 큰 조건을 만족하는 데이터 조회
//	@Test
//	public void testQuesry2() {
//		Pageable pageable = PageRequest.of(0, 10, Sort.by("poid").descending());
//
//		QPost qPost = QPost.post;
//
//		String keyword = "3";
//		String keyword2 = "1";
//
//		BooleanBuilder builder = new BooleanBuilder();
//
//		BooleanExpression exTitle = qPost.potitle.contains(keyword);
//		BooleanExpression exContent = qPost.pocontent.contains(keyword2);
//		BooleanExpression exAll = exTitle.and(exContent);
//
//		builder.and(exAll);
//		builder.and(qPost.poid.gt(10L));
//
//		Page<Post> result = postRepository.findAll(builder, pageable);
//
//		result.stream().forEach(post -> {
//			System.out.println(post);
//		});
//	}
	
	// 성공
//	@Transactional
//	@Test
//	public void testReadPost() {
//		Optional<Post> result = postRepository.findById(100L);
//		
//		Post post = result.get();
//		
//		System.out.println(post);
//		System.out.println(post.getUser().getUid());
//	}
	
	// 성공
//	@Test
//	public void testReadWithWriter() {
//		Object result = postRepository.getPostWithUid(100L);
//		Object[] arr = (Object[]) result;
//		
//		System.out.println("-----------------------");
//		System.out.println(Arrays.toString(arr));
//	}
	
//	@Test
//	public void testGetPostByPid() {
//		Object result = postRepository.getPostWithUid(100L);
//		Object[] arr = (Object[])result;
//		System.out.println(Arrays.toString(arr));
//	}
	
	// 성공
//	 @Test
//	 public void testGetMovieWithAll(){
//
//		 List<Object[]> result = postRepository.getPostWithAll(93L);
//
//		 System.out.println(result);
//
//		 for (Object[] arr : result){
//			 System.out.println(Arrays.toString(arr));
//		 }
//	 }
	
}
