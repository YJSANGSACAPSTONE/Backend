package com.planner.godsaeng.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.planner.godsaeng.dto.BoardDTO;
import com.planner.godsaeng.dto.PageRequestDTO;
import com.planner.godsaeng.dto.PageResultDTO;
import com.planner.godsaeng.dto.PostDTO;
import com.planner.godsaeng.entity.Post;

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
		
		System.out.println("----------end of boardservice.register()");
	}
	
	@Test
	public void testList() {
	PageRequestDTO pageRequestDTO = new PageRequestDTO();
	
	PageResultDTO<PostDTO, Object[]> result = postService.getList(pageRequestDTO);
	
	for (PostDTO boardDTO : result.getDtoList()) {
		System.out.println(boardDTO);
	}
	}
	
	@Test
	public void testGet() {
		Long pid = 100L;
		PostDTO postDTO = postService.get(pid);
		System.out.println(postDTO);
	}
	
	@Test
	public void testRemove() {
		Long pid = 1L;
		postService.removeWithReplies(pid);
	}
	
	@Test
	public void testModify() {
		PostDTO postDTO = PostDTO.builder()
				.p_id(3L)
				.p_title("제목 변경")
				.p_content("내용 변경")
				.build();
		postService.modify(postDTO);
	}
	
}
