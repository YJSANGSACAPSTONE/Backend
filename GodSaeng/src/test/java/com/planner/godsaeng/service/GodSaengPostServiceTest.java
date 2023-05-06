package com.planner.godsaeng.service;

import java.util.Collections;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.planner.godsaeng.dto.BoardDTO;
import com.planner.godsaeng.dto.PageRequestDTO;
import com.planner.godsaeng.dto.PageResultDTO;
import com.planner.godsaeng.dto.PostDTO;
import com.planner.godsaeng.dto.PostImageDTO;
import com.planner.godsaeng.entity.Post;
import com.planner.godsaeng.entity.PostImage;
import com.planner.godsaeng.repository.PostImageRepository;

@SpringBootTest
public class GodSaengPostServiceTest {
	
	@Autowired
	private PostService postService;
	
//	// 성공!
//	@Test
//	public void testRegister() {
//	    PostDTO postDTO = PostDTO.builder()
//	        .po_title("게시판 타이틀 제목 테스트...")
//	        .po_content("게시판 내용 테스트....")
//	        .u_id("userID30")
//	        .b_id(1)
//	        .imageDTOList(Collections.singletonList(PostImageDTO.builder()
//	            .uuid(UUID.randomUUID().toString())
//	            .imgName("test" + 30 + ".jpg")
//	            .build()))
//	        .build();
//
//	    Long poid = postService.register(postDTO);
//
//	    System.out.println("----------end of boardservice.register()");
//	}
	
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
//		Long pid = 11L;
//		PostDTO postDTO = postService.getPost(pid);
//		System.out.println(postDTO);
//	}

////	성공!
//	@Test 
//	public void testRemove() {
//		Long pid = 19L;
//		postService.removeWithImages(pid);
//	}
	
//	@Test
//	public void testModify() {
//		PostDTO postDTO = PostDTO.builder()
//				.po_id(9L)
//				.po_title("제목 변경")
//				.po_content("내용 변경")
//				.build();
//		postService.modify(postDTO);
//	}
	
}
