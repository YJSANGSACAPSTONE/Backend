package com.planner.godsaeng.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.planner.godsaeng.dto.PageRequestDTO;
import com.planner.godsaeng.dto.PageResultDTO;
import com.planner.godsaeng.dto.PostDTO;
import com.planner.godsaeng.entity.Post;
import com.planner.godsaeng.repository.PostRepository;
import com.planner.godsaeng.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/post/")
@Log4j2
@RequiredArgsConstructor
public class PostController {
	
	private final PostRepository postRepository;
	private final PostService postService;
	
//	@GetMapping("/list")
//	public void list(PageRequestDTO pageRequestDTO, Model model) {
//		log.info("list......................" + pageRequestDTO);
//		
//		model.addAttribute("result", postService.getList(pageRequestDTO));
//	}
	
	//rest
	@GetMapping("/list")
	public ResponseEntity<?> list(PageRequestDTO pageRequestDTO) {
	    log.info("list......................" + pageRequestDTO);

	    PageResultDTO<PostDTO, Object[]> postList = postService.getList(pageRequestDTO);

	    return ResponseEntity.ok(postList);
	}
	
	@GetMapping("/register")
	public void regiser() {
		log.info("register get............");
	}
	
	// rest
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody PostDTO dto) {
	    log.info("dto...................." + dto);

	    Long poid = postService.register(dto);

	    return ResponseEntity.ok(poid);
	}
	////
//	@PostMapping("/register")
//	public String registerPost(PostDTO dto, RedirectAttributes redirectAttributes) {
//		log.info("dto...................." + dto);
//		
//		Long pid = postService.register(dto);
//		
//		log.info("PID: " + pid);
//		
//		redirectAttributes.addFlashAttribute("msg", pid);
//		
//		return "redirect:/post/list";
//	}
	
//	@GetMapping({"/read", "/modify"})
//	public void read(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Long pid, Model model) {
//		log.info("PID: " + pid);
//		
//		PostDTO postDTO = postService.getPost(pid);
//		
//		log.info(postDTO);
//		
//		model.addAttribute("dto", postDTO);
//	}
	
	// rest
	// test: http://localhost:8070/post/read?poid=223 (GET)
	@GetMapping({"/read", "/modify"})
	public ResponseEntity<PostDTO> read(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Long poid,
	                                    HttpServletRequest request, HttpServletResponse response) {
	    log.info("POID: " + poid);

	    // 게시물 조회
	    PostDTO postDTO = postService.getPost(poid);

	    // 조회수 처리
	    postService.viewCountValidation(postDTO, request, response);

	    log.info(postDTO);

	    return ResponseEntity.ok().body(postDTO);
	}
	
//	@PostMapping("/remove")
//	public String remove(long pid, RedirectAttributes redirectAttributes) {
//		log.info("pid: " + pid);
//		postService.removeWithImages(pid);
//		redirectAttributes.addFlashAttribute("msg", pid);
//		
//		return "redirect:/post/list";
//	}
	
	// rest 
	// test: http://localhost:8070/post/remove/222 (POST)
	@DeleteMapping("/remove/{poid}")
	public ResponseEntity<String> remove(@PathVariable long poid) {
		log.info("poid: " + poid);
		postService.removeWithAll(poid);
		return ResponseEntity.ok().body("Post with ID " + poid + " has been removed.");
	}
	
//	@PostMapping("/modify")
//	public String modify(PostDTO dto, @ModelAttribute("requestBTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes) {
//		log.info("post modify.......................");
//		log.info("dto: " + dto);
//		
//		postService.modify(dto);
//		
//		redirectAttributes.addAttribute("page", requestDTO.getPage());
//		redirectAttributes.addAttribute("type", requestDTO.getType());
//		redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());
//		
//		redirectAttributes.addAttribute("pid", dto.getP_id());
//		
//		return "redirect:/post/read";
//	}
	
	@PutMapping("/modify/{poid}")
	public ResponseEntity<String> modify(@PathVariable long poid, @RequestBody PostDTO dto) {
		log.info("post modify.......................");
		log.info("dto: " + dto);
		postService.modify(dto);

		return ResponseEntity.ok().body("Post with ID " + poid + " has been modified.");
	}
	
//	@GetMapping("/posts/{poid}")
//    public String postInfo(@PathVariable("poid") Long poid,
//                           HttpServletRequest request,
//                           HttpServletResponse response,
//                           Model model) {
//		
//		// poid에 해당하는 게시글을 조회
//        Post post = postRepository.findByPoid(poid)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다."));
//        
//        // viewCountValidation 메서드를 호출하여 조회수 관련 로직을 처리
//        postService.viewCountValidation(post, request, response);
//        
//        // 조회된 게시글을 모델에 추가합니다.
//        model.addAttribute("post", post);
//        
//        // post-info를 렌더링하기 위한 뷰 이름을 반환
//        return "post/post-info";
//    }

	@PostMapping("/like/{poid}")
	public ResponseEntity<String> likePost(@PathVariable Long poid, @RequestBody Map<String, String> requestBody) {
	    String uid = requestBody.get("uid");
	    postService.likePost(poid, uid);
	    return ResponseEntity.ok().body("Post with ID " + poid + " has been liked.");
	}

}
