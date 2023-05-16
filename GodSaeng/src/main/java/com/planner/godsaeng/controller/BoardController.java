package com.planner.godsaeng.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.planner.godsaeng.dto.BoardDTO;
import com.planner.godsaeng.entity.Board;
import com.planner.godsaeng.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
//   BoardService service = new BoardService();
//   
//   @GetMapping("/write")
//   public String Mainpage() {
//      return "publishing/pages/board/write";
//   }
//   
//   @PostMapping("/add")
//   public String addBoard(BoardDTO b) {
//      service.InsertBoard(b);
//      return "redirect: /board/list";
//   }
//
//   @GetMapping("/list")
//   public String listBoard(Model m) {
//      List<Board> list = service.ReadBoard();
//      m.addAttribute("list", list);
//      return "publishing/pages/board/list";
//   }
//
//   @PostMapping("/updateboard")
//   public String updateBoard(BoardDTO b) {
//      service.UpdateBoard(b);
//      return null;
//   }
//
//   @GetMapping("/deleteboard")
//   public String deleteBoard(BoardDTO b) {
//      service.DeleteBoard(b.getB_id());
//
//      return null;
//   }
}
