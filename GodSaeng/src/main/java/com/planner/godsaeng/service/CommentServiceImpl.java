package com.planner.godsaeng.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planner.godsaeng.entity.Post;
import com.planner.godsaeng.entity.Comment;
import com.planner.godsaeng.dto.CommentDTO;
import com.planner.godsaeng.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
	@Autowired
    private final CommentRepository commentRepository;

    @Override
    public List<CommentDTO> getListOfPost(Long poid) {

        Post post = Post.builder().poid(poid).build();

        List<Comment> result = commentRepository.findByPost(post);

        return result.stream().map(postComment -> entityToDto(postComment)).collect(Collectors.toList());
    }

    @Override
    public Long register(CommentDTO postCommentDTO) {

        Comment postComment = dtoToEntity(postCommentDTO);

        commentRepository.save(postComment);

        return postComment.getCommid();
    }

    @Override
    public void modify(CommentDTO postCommentDTO) {

        Optional<Comment> result = commentRepository.findById(postCommentDTO.getComm_id());

        if(result.isPresent()){
            Comment postComment = result.get();
            postComment.changeText(postCommentDTO.getComm_text());
            commentRepository.save(postComment);
        }

    }

    @Override
    public void remove(Long commid) {

        commentRepository.deleteById(commid);

    }
}
