package com.planner.godsaeng.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planner.godsaeng.dto.CommentDTO;
import com.planner.godsaeng.entity.Comment;
import com.planner.godsaeng.repository.CommentRepository;

@Service
public class CommentService {
	
	Comment comment = null;
	@Autowired
	CommentRepository commentRepository;

	public void testClass() {
		System.out.println("객체생성 확인 ---" + commentRepository.getClass().getName());
	}
	   
	   //유저 회원가입(INSERT)
	   public boolean InsertComment(CommentDTO c) {
	      comment = Comment.builder()
	            .c_id(c.getC_id())
	            .c_writer(c.getC_writer())
	            .c_content(c.getC_content())
	            .c_time(c.getC_time())
	            .build();
	      
	      try {
	    	  commentRepository.save(comment);
	         return true;
	      }catch(Exception e) {
	         e.printStackTrace();
	         return false;
	      }
	   }
	   
	   
//	   
//		private Long id;
//		private String comment;
//		private String writer;
//		private String title;
//		private String content;
//		private Date regDate;
	   
	   //유저 정보 목록
	   public List<Comment> ReadComment(){
	      List<Comment> list = new ArrayList<Comment>();
	      list = commentRepository.findAll();
	      return list;
	   }
	   
	   //유저 정보 수정
	   public boolean UpdateComment(CommentDTO c) {
	      comment = Comment.builder()
		        .c_id(c.getC_id())
		        .c_writer(c.getC_writer())
		        .c_content(c.getC_content())
	            .c_time(c.getC_time())
		        .build();
	      try {
	         commentRepository.save(comment);
	         return true;
	      }catch(Exception e) {
	         e.printStackTrace();
	         return false;
	      }
	   }
	   
	   //유저 정보 삭제
	   public boolean DeleteComment(long c_id) {
	      try {
	         commentRepository.deleteById(c_id);
	         return true;
	      }catch(Exception e) {
	         e.printStackTrace();
	         return false;
	      }
	   }
}

