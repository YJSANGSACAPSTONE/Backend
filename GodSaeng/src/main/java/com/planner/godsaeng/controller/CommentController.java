package com.planner.godsaeng.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.planner.godsaeng.dto.CommentDTO;
import com.planner.godsaeng.entity.Comment;
import com.planner.godsaeng.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService service;

	@PostMapping("/addcomment")
	public String addComment(@ModelAttribute("comment") CommentDTO c) {
		service.InsertComment(c);
		return "comment";
	}

//	@PostMapping("/addcomment")
//	public String addComment(
//	    @RequestParam(name = "c_time") String c_time,
//	    @RequestParam(name = "c_writer") String c_writer,
//	    @RequestParam(name = "c_content") String c_content) {
//	    CommentDTO commentDTO = new CommentDTO();
//	    commentDTO.setC_time(c_time);
//	    commentDTO.setC_writer(c_writer);
//	    commentDTO.setC_content(c_content);
//	    service.InsertComment(commentDTO);
//	    return "index";
//	}
	
	
	@GetMapping("/listcomment")
	public String listComment(Model m) {
		List<Comment> list = service.ReadComment();
		m.addAttribute("list", list);
		return "comment";
	}

	@PostMapping("/updatecomment")
	public String updateComment(CommentDTO c) {
		service.UpdateComment(c);
		return null;
	}

	@GetMapping("/deletecomment")
	public String deleteComment(CommentDTO c) {
		service.DeleteComment(c.getC_id());
		

		return null;
	}
}