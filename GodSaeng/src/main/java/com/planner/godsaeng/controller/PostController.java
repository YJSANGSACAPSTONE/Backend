package com.planner.godsaeng.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.planner.godsaeng.dto.PageRequestDTO;
import com.planner.godsaeng.dto.PageResultDTO;
import com.planner.godsaeng.dto.PostDTO;
import com.planner.godsaeng.repository.PostRepository;
import com.planner.godsaeng.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/post/")
@Log4j2
@RequiredArgsConstructor
public class PostController {
   private final PostService postService;
   
//   @GetMapping("/list")
//   public void list(PageRequestDTO pageRequestDTO, Model model) {
//      log.info("list......................" + pageRequestDTO);
//      
//      model.addAttribute("result", postService.getList(pageRequestDTO));
//   }
   
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
   
//   @PostMapping("/register")
//   public String registerPost(PostDTO dto, RedirectAttributes redirectAttributes) {
//      log.info("dto...................." + dto);
//      
//      Long pid = postService.register(dto);
//      
//      log.info("PID: " + pid);
//      
//      redirectAttributes.addFlashAttribute("msg", pid);
//      
//      return "redirect:/post/list";
//   }
   
//   @GetMapping({"/read", "/modify"})
//   public void read(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Long pid, Model model) {
//      log.info("PID: " + pid);
//      
//      PostDTO postDTO = postService.getPost(pid);
//      
//      log.info(postDTO);
//      
//      model.addAttribute("dto", postDTO);
//   }
   
   // rest
   // test: http://localhost:8070/post/read?pid=223 (GET)
   @GetMapping({"/read", "/modify"})
   public ResponseEntity<PostDTO> read(@ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Long poid) {
      log.info("POID: " + poid);
      PostDTO postDTO = postService.getPost(poid);

      log.info(postDTO);

      return ResponseEntity.ok().body(postDTO);
   }
   
//   @PostMapping("/remove")
//   public String remove(long pid, RedirectAttributes redirectAttributes) {
//      log.info("pid: " + pid);
//      postService.removeWithImages(pid);
//      redirectAttributes.addFlashAttribute("msg", pid);
//      
//      return "redirect:/post/list";
//   }
   
   // rest 
   // test: http://localhost:8070/post/remove/222 (POST)
   @PostMapping("/remove/{pid}")
   public ResponseEntity<String> remove(@PathVariable long poid) {
      log.info("poid: " + poid);
      postService.removeWithImages(poid);
      return ResponseEntity.ok().body("Post with ID " + poid + " has been removed.");
   }
   
//   @PostMapping("/modify")
//   public String modify(PostDTO dto, @ModelAttribute("requestBTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes) {
//      log.info("post modify.......................");
//      log.info("dto: " + dto);
//      
//      postService.modify(dto);
//      
//      redirectAttributes.addAttribute("page", requestDTO.getPage());
//      redirectAttributes.addAttribute("type", requestDTO.getType());
//      redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());
//      
//      redirectAttributes.addAttribute("pid", dto.getP_id());
//      
//      return "redirect:/post/read";
//   }
   
   @PostMapping("/modify/{pid}")
   public ResponseEntity<String> modify(@PathVariable long poid, @RequestBody PostDTO dto) {
      log.info("post modify.......................");
      log.info("dto: " + dto);
      postService.modify(dto);

      return ResponseEntity.ok().body("Post with ID " + poid + " has been modified.");
   	}
}