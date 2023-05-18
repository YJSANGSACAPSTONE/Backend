package com.planner.godsaeng.service;

import java.util.List;

import com.planner.godsaeng.dto.CommentDTO;
import com.planner.godsaeng.entity.Comment;
import com.planner.godsaeng.entity.User;
import com.planner.godsaeng.entity.Post;

public interface CommentService {
	
	// 모든 post를 가져온다
	List<CommentDTO> getListOfPost(Long poid);
	
	// 댓굴 추가
	Long register(CommentDTO postCommentDTO);
	
	// 댓글 수정
	void modify(CommentDTO postCommentDTO);
	
	// 댓글 삭제
	void remove(Long commId);
	
	default Comment dtoToEntity(CommentDTO postCommentDTO){

		Comment postComment = Comment.builder()
				.commid(postCommentDTO.getComm_id())
				.post(Post.builder().poid(postCommentDTO.getPo_id()).build())
				.user(User.builder().uid(postCommentDTO.getU_id()).build())
				.commtext(postCommentDTO.getComm_text())
				.build();

		return postComment;
	}

	default CommentDTO entityToDto(Comment postComment){

		CommentDTO postCommentDTO = CommentDTO.builder()
				.comm_id(postComment.getCommid())
				.po_id(postComment.getPost().getPoid())
				.u_id(postComment.getUser().getUid())
				.comm_text(postComment.getCommtext())
				.regDate(postComment.getRegDateTime())
				.modDate(postComment.getModDateTime())
				.build();

		return postCommentDTO;
	}
}
