package com.planner.godsaeng.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
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

import com.planner.godsaeng.dto.PageRequestDTO;
import com.planner.godsaeng.dto.PageResultDTO;
import com.planner.godsaeng.dto.PostDTO;
import com.planner.godsaeng.entity.Post;
import com.planner.godsaeng.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/post/")
@Log4j2
@RequiredArgsConstructor
public class PostController {
	
	private final PostService postService;
	
	@GetMapping("/list")
	public ResponseEntity<?> list(PageRequestDTO pageRequestDTO) {
	    log.info("list......................" + pageRequestDTO);

	    PageResultDTO<PostDTO, Object[]> postList = postService.getList(pageRequestDTO);

	    return ResponseEntity.ok(postList);
	}
	
	@GetMapping("/list/{bid}")
	public ResponseEntity<?> listByBoard(PageRequestDTO pageRequestDTO, @PathVariable int bid) {
	    log.info("listByBoard - bid: {}", bid);

	    PageResultDTO<PostDTO, Object[]> postList = postService.getListByBoard(pageRequestDTO, bid);

	    return ResponseEntity.ok(postList);
	}
	
	@GetMapping("/register")
	public void regiser() {
		log.info("register get............");
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody PostDTO dto) {
	    log.info("dto...................." + dto);

	    Long poid = postService.register(dto);

	    return ResponseEntity.ok(poid);
	}

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
	
	@DeleteMapping("/remove/{poid}")
	public ResponseEntity<String> remove(@PathVariable long poid) {
		log.info("poid: " + poid);
		postService.removeWithAll(poid);
		return ResponseEntity.ok().body("Post with ID " + poid + " has been removed.");
	}
	
	@PutMapping("/modify/{poid}")
	public ResponseEntity<String> modify(@PathVariable long poid, @RequestBody PostDTO dto) {
		log.info("post modify.......................");
		log.info("dto: " + dto);
		postService.modify(dto);

		return ResponseEntity.ok().body("Post with ID " + poid + " has been modified.");
	}
	
	@GetMapping("/liked/{poid}/{uid}")
	public ResponseEntity<Boolean> isPostLikedByUser(@PathVariable Long poid, @PathVariable String uid) {
	    boolean liked = postService.isPostLikedByUser(poid, uid);
	    return ResponseEntity.ok().body(liked);
	}
	
	// test: http://localhost:8080/post/popular?limit=10
	// limit = 출력 개수
    @GetMapping("/popular")
    public ResponseEntity<List<Post>> getPopularPosts(@RequestParam int limit) {
        List<Post> popularPosts = postService.getPopularPosts(limit);
        return ResponseEntity.ok(popularPosts);
    }
    

	@PostMapping("/like/{poid}")
	public ResponseEntity<Boolean> likePost(@PathVariable Long poid, @RequestBody Map<String, String> requestBody) {
	    String uid = requestBody.get("uid");
	    boolean liked = postService.likePost(poid, uid);
	    return ResponseEntity.ok().body(liked);
	}
    

}
