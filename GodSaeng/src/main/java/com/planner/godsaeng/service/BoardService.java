package com.planner.godsaeng.service;

import com.planner.godsaeng.dto.BoardDTO;
import com.planner.godsaeng.entity.Board;
import com.planner.godsaeng.entity.User;

public interface BoardService {

	
	// 게시판 생성
	int register(BoardDTO boardDTO);
	
	// 게시판 삭제
	void removeWithAll(int poid);
	
	default BoardDTO entityToDto(Board board) {
		BoardDTO boardDTO = BoardDTO.builder()
				.u_id(board.getUser().getUid())
				.b_id(board.getBid())
				.b_name(board.getBname())
				.build();
		
		return boardDTO;
	}
	
	default Board dtoToEntity(BoardDTO boardDTO) {
		User user = User.builder()
				.uid(boardDTO.getU_id()).build();
		
		Board board = Board.builder()
				.user(user)
				.bid(boardDTO.getB_id())
				.bname(boardDTO.getB_name())
				.build();
		
		return board;
		
	}
      

}