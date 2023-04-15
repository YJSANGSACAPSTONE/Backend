package com.planner.godsaeng.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.planner.godsaeng.dto.BoardDTO;
import com.planner.godsaeng.entity.Board;
import com.planner.godsaeng.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	   Board board = null;
	   @Autowired
	   BoardRepository boardRepository;
	   
	   //유저 회원가입(INSERT)
	   public boolean InsertBoard (BoardDTO b) {
	      board = Board.builder()
	            .b_id(b.getB_id())
	            .u_id(b.getU_id())
	            .b_date(b.getB_date())
	            .b_title(b.getB_title())
	            .b_content(b.getB_content())
	            .b_image(b.getB_image())
	            .b_category(b.getB_category())
	            .b_count(b.getB_count())
	            .build();
	      
	      try {
	         boardRepository.save(board);
	         return true;
	      }catch(Exception e) {
	         e.printStackTrace();
	         return false;
	      }
	   }
	   
	   //유저 정보 목록
	   public List<Board> ReadBoard(){
	      List<Board> list = new ArrayList<Board>();
	      list = boardRepository.findAll();
	      return list;
	   }
	   
	   //유저 정보 수정
	   public boolean UpdateBoard(BoardDTO b) {
	      board = Board.builder()
	    		  .b_id(b.getB_id())
	    		  .u_id(b.getU_id())
	    		  .b_date(b.getB_date())
	    		  .b_title(b.getB_title())
	    		  .b_content(b.getB_content())
	    		  .b_image(b.getB_image())
	    		  .b_category(b.getB_category())
	    		  .b_count(b.getB_count())
	            .build();
	      try {
	         boardRepository.save(board);
	         return true;
	      }catch(Exception e) {
	         e.printStackTrace();
	         return false;
	      }
	   }
	   
	   //유저 정보 삭제
	   public boolean DeleteBoard(String u_id) {
	      try {
	         boardRepository.deleteById(u_id);
	         return true;
	      }catch(Exception e) {
	         e.printStackTrace();
	         return false;
	      }
	   }
}
