package com.planner.godsaeng.service;

import java.util.List;

import com.planner.godsaeng.dto.CommentDTO;
import com.planner.godsaeng.entity.Comment;

public interface CommentService {
	
	// 모든 post를 가져온다
	List<CommentDTO> getListOfPost(Long pid);
	
	// 댓굴 추가
	Long register(CommentDTO postCommentDTO);
	
	// 댓글 수정
	void modify(CommentDTO postCommentDTO);
	
	// 댓글 삭제
	void remove(Long commentId);
	
	default Comment dtoToEntity(CommentDTO postCommentDTO){

		Comment postComment = Comment.builder()
				.commentId(postCommentDTO.getCommentId())
				.post(Comment.builder().pid(postCommentDTO.getP_id()).build())
				.member(Comment.builder().uid(postCommentDTO.getU_id()).build())
				.grade(postCommentDTO.getGrade())
				.text(postCommentDTO.getText())
				.build();

		return postComment;
	}

	default CommentDTO entityToDto(Comment movieReview){

		CommentDTO movieReviewDTO = CommentDTO.builder()
				.reviewnum(movieReview.getReviewnum())
				.mno(movieReview.getMovie().getMno())
				.mid(movieReview.getMember().getMid())
				.nickname(movieReview.getMember().getNickname())
				.email(movieReview.getMember().getEmail())
				.grade(movieReview.getGrade())
				.text(movieReview.getText())
				.regDate(movieReview.getRegDate())
				.modDate(movieReview.getModDate())
				.build();

		return movieReviewDTO;
	}
}