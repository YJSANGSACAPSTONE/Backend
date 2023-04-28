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
	   public boolean InsertBoard(BoardDTO b) {
	      board = Board.builder()
	            .bid(b.getB_id())		// 게시판 id
	            .aid(b.getA_id())		// 관리자 id
	            .bname(b.getB_name())	// 게시판 이름
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
				   .bid(b.getB_id())		// 게시판 id
				   .aid(b.getA_id())		// 관리자 id
				   .bname(b.getB_name())	// 게시판 이름
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
