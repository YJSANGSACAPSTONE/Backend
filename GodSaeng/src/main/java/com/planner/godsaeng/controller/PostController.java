package com.planner.godsaeng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.planner.godsaeng.dto.PageRequestDTO;
import com.planner.godsaeng.dto.PostDTO;
import com.planner.godsaeng.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/post/")
@Log4j2
@RequiredArgsConstructor
public class PostController {
	private final PostService postService;
	
	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
		log.info("list......................" + pageRequestDTO);
		
		model.addAttribute("result", postService.getList(pageRequestDTO));
	}
	
	@GetMapping("/register")
	public void regiser() {
		log.info("register get............");
	}
	
	@PostMapping("/register")
	public String registerPost(PostDTO dto, RedirectAttributes redirectAttributes) {
		log.info("dto...................." + dto);
		
		Long pid = postService.register(dto);
		
		log.info("PID: " + pid);
		
		redirectAttributes.addFlashAttribute("msg", pid);
		
		return "redirect:/post/list";
	}
	
	@GetMapping({"/read", "/modify"})
	public void read(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Long pid, Model model) {
		log.info("PID: " + pid);
		
		PostDTO postDTO = postService.get(pid);
		
		log.info(postDTO);
		
		model.addAttribute("dto", postDTO);
	}
	
	@PostMapping("/remove")
	public String remove(long pid, RedirectAttributes redirectAttributes) {
		log.info("pid: " + pid);
		postService.removeWithReplies(pid);
		redirectAttributes.addFlashAttribute("msg", pid);
		
		return "redirect:/post/list";
	}
	
	@PostMapping("/modify")
	public String modify(PostDTO dto, @ModelAttribute("requestBTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes) {
		log.info("post modify.......................");
		log.info("dto: " + dto);
		
		postService.modify(dto);
		
		redirectAttributes.addAttribute("page", requestDTO.getPage());
		redirectAttributes.addAttribute("type", requestDTO.getType());
		redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());
		
		redirectAttributes.addAttribute("pid", dto.getP_id());
		
		return "redirect:/post/read";
	}
}
