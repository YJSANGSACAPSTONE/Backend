package com.planner.godsaeng.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.planner.godsaeng.dto.BoardDTO;
import com.planner.godsaeng.entity.Board;
import com.planner.godsaeng.repository.BoardRepository;
import com.planner.godsaeng.repository.CommentRepository;
import com.planner.godsaeng.repository.PostImageRepository;
import com.planner.godsaeng.repository.PostLikeRepository;
import com.planner.godsaeng.repository.PostRepository;
import com.planner.godsaeng.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardRepository boardRepository;
	
    @Override
    @Transactional
    public int register(BoardDTO boardDTO) {
    	log.info("---BoardServiceImpl register()---" + boardDTO);;
    	
        Board board = dtoToEntity(boardDTO);
        boardRepository.save(board);
        return board.getBid();
    }
    
    @Override
    @Transactional
    public void removeWithAll(int bid) {
        boardRepository.deleteById(bid);
    }

}
