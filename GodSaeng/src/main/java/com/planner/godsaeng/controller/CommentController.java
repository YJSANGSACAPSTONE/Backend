package com.planner.godsaeng.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.planner.godsaeng.dto.CommentDTO;
import com.planner.godsaeng.service.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
	
	@Autowired
    private final CommentService commentService;

    @GetMapping("/{poid}/all") // 결과데이터 : CommentDTO 리스트, 해당게시물의 모든 댓글 반환
    public ResponseEntity<List<CommentDTO>> getList(@PathVariable("poid") Long poid){

        List<CommentDTO> commentDTOList = commentService.getListOfPost(poid);

        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
    }

    @PostMapping("/{poid}") // 결과데이터 : 생성된 댓글 번호 , 새로운 댓글등록
    public ResponseEntity<Long> addComment(@RequestBody CommentDTO postCommentDTO){
    	
        Long commid = commentService.register(postCommentDTO);

        return new ResponseEntity<>(commid, HttpStatus.OK);
    }

    @PutMapping("/{poid}/{commid}") // 결과데이터 : 댓글의 수정 성공 여부, 댓글 수정
    public ResponseEntity<Long> modifyComment(@PathVariable Long poid, @PathVariable Long commid, @RequestBody CommentDTO postCommentDTO){
        postCommentDTO.setComm_id(commid); // commid 값을 CommentDTO에 설정

        commentService.modify(postCommentDTO);

        return new ResponseEntity<>(commid, HttpStatus.OK);
    }
   
    @DeleteMapping("/{poid}/{commid}") // 댓글 삭제 
    public ResponseEntity<Long> removeComment(@PathVariable Long commid){

        commentService.remove(commid);

        return new ResponseEntity<>(commid, HttpStatus.OK);
    }

}