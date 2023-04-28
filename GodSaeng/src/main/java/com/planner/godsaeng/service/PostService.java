package com.planner.godsaeng.service;

import com.planner.godsaeng.dto.PageRequestDTO;
import com.planner.godsaeng.dto.PageResultDTO;
import com.planner.godsaeng.dto.PostDTO;
import com.planner.godsaeng.entity.Board;
import com.planner.godsaeng.entity.Post;
import com.planner.godsaeng.entity.User;

public interface PostService {
	Long register(PostDTO dto);

	PageResultDTO<PostDTO, Object[]> getList(PageRequestDTO pageRequestDTO);
	
	PostDTO get(Long pid);
	
	void removeWithReplies(Long pid);

	void modify(PostDTO postDTO);

	default Post dtoToEntiy(PostDTO dto) {
		User user = User.builder()
				.uid(dto.getU_id()).build();
		
		Board board = Board.builder()
				.bid(dto.getB_id()).build();
				
		Post post = Post.builder()
				.uid(user)
				.bid(board)
				.pid(dto.getP_id())
				.ptitle(dto.getP_title())
				.pcontent(dto.getP_content())
				.phitCount(dto.getP_hitCount())
				.plike(dto.getP_like())
				.psecret(dto.isP_secret())
				.build();
		return post;
	}

	default PostDTO entityToDto(Post post, User user) {
		PostDTO postDTO = PostDTO.builder()
				.u_id(user.getUid())
				.b_id(post.getBid().getBid())
				.p_id(post.getPid())
				.p_title(post.getPtitle())
				.p_content(post.getPcontent())
				.p_hitCount(post.getPhitCount())
				.p_like(post.getPlike())
				.p_secret(post.isPsecret())
				.build();
		
		return postDTO;
	}
}
