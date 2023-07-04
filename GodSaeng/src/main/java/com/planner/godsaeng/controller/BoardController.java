package com.planner.godsaeng.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.planner.godsaeng.dto.BoardDTO;
import com.planner.godsaeng.security.jwt.JwtAuthentication;
import com.planner.godsaeng.service.BoardService;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public ResponseEntity<Integer> createBoard(@AuthenticationPrincipal JwtAuthentication user, @RequestBody BoardDTO boardDTO) {
        int boardId = boardService.register(boardDTO);
        return new ResponseEntity<>(boardId, HttpStatus.OK);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<Void> deleteBoard(@AuthenticationPrincipal JwtAuthentication user, @PathVariable int boardId) {
        boardService.removeWithAll(boardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


