package com.planner.godsaeng.service;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.planner.godsaeng.dto.BoardDTO;
import com.planner.godsaeng.dto.PageRequestDTO;
import com.planner.godsaeng.dto.PageResultDTO;
import com.planner.godsaeng.dto.PostDTO;
import com.planner.godsaeng.entity.Post;
import com.planner.godsaeng.entity.PostImage;
import com.planner.godsaeng.repository.PostImageRepository;

@SpringBootTest
public class GodSaengPostServiceTest {
	
	@Autowired
	private PostService postService;
	
	@Test 
	public void testRegister() {
		PostDTO postDTO = PostDTO.builder()
				.p_title("게시판 타이틀 제목 테스트...")
				.p_content("게시판 내용 테스트....")
				.u_id("userID27")
				.b_id(1)
				.build();

		Long pid = postService.register(postDTO);
		
		PostImage postImage = PostImage.builder()
                .uuid(UUID.randomUUID().toString())
                .imgName("test" + 27 +".jpg").build();
		
		System.out.println("----------end of boardservice.register()");
	}
	
//	// 성공
//	@Test
//	public void testList() {
//	    // Create a PageRequestDTO object
//	    PageRequestDTO pageRequestDTO = new PageRequestDTO();
//	    
//	    // Call the getList() method and get the result
//	    PageResultDTO<PostDTO, Object[]> result = postService.getList(pageRequestDTO);
//	    
//	    // Iterate through the Object[] objects in the result and print them
//	    for (PostDTO postDTO : result.getDtoList()) {
//	        System.out.println(postDTO);
//	    }
//	}
	
////	성공!	
//	@Test
//	public void testGet() {
//		Long pid = 100L;
//		PostDTO postDTO = postService.getPost(pid);
//		System.out.println(postDTO);
//	}

////	성공!
//	@Test 
//	public void testRemove() {
//		Long pid = 99L;
//		postService.removeWithImages(pid);
//	}
	
//	@Test
//	public void testModify() {
//		PostDTO postDTO = PostDTO.builder()
//				.p_id(2L)
//				.p_title("제목 변경")
//				.p_content("내용 변경")
//				.build();
//		postService.modify(postDTO);
//	}
	
}
