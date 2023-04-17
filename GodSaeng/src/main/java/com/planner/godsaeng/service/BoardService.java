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
	   
	   // 게시판 작성 (Insert)
	   public boolean InsertBoard (BoardDTO b) {
	      board = Board.builder()
	            .b_id(b.getB_id())
	            .b_number(b.getB_number())
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
	   
	   // 게시판 조회 (Read)
	   public List<Board> ReadBoard(){
	      List<Board> list = new ArrayList<Board>();
	      list = boardRepository.findAll();
	      return list;
	   }
	   
	   // 게시판 수정 (Update)
	   public boolean UpdateBoard(BoardDTO b) {
	      board = Board.builder()
	    		  .b_id(b.getB_id())
	    		  .b_number(b.getB_number())
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
	   
	   // 게시판 삭제 (Delete)
	   public boolean DeleteBoard(long b_id) {
	      try {
	         boardRepository.deleteById(b_id);
	         return true;
	      }catch(Exception e) {
	         e.printStackTrace();
	         return false;
	      }
	   }
	   
	   
}
