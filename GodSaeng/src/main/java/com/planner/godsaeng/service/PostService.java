package com.planner.godsaeng.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.annotations.Until;
import com.planner.godsaeng.dto.PageRequestDTO;
import com.planner.godsaeng.dto.PageResultDTO;
import com.planner.godsaeng.dto.PostDTO;
import com.planner.godsaeng.dto.PostImageDTO;
import com.planner.godsaeng.entity.Board;
import com.planner.godsaeng.entity.Post;
import com.planner.godsaeng.entity.PostImage;
import com.planner.godsaeng.entity.User;

public interface PostService {
	
	// 게시글 등록
	Long register(PostDTO postDTO);
	
	// 목록처리
	PageResultDTO<PostDTO, Object[]> getList(PageRequestDTO pageRequestDTO);
	
	// bid에 대한 목록처리
	PageResultDTO<PostDTO, Object[]> getListByBoard(PageRequestDTO pageRequestDTO, int bid);
	
	// 게시물 조회
	PostDTO getPost(Long poid);
	
	// 게시물 삭제
	void removeWithAll(Long poid);
	
	// 게시물 수정
	void modify(PostDTO postDTO);
	
	// 조회수 처리
	void viewCountValidation(PostDTO postDTO, HttpServletRequest request, HttpServletResponse response);
	
	// 게시물 좋아요
	void likePost(Long poid, String uid);
	
	// 인기글
	List<Post> getPopularPosts(int limit);
    
	default PostDTO entityToDto(Post post, List<PostImage> postImages, Long commentCnt, Long likeCnt) {
		PostDTO postDTO = PostDTO.builder()
				.u_id(post.getUser().getUid())
				.b_id(post.getBoard().getBid())
				.po_id(post.getPoid())
				.po_title(post.getPotitle())
				.po_content(post.getPocontent())
				.po_regDate(post.getRegDateTime())
				.po_modDate(post.getModDateTime())
				.po_hitcount(post.getPohitcount())
				.po_secret(post.isPosecret())
				.build();
		
	    if (postImages != null) { // postImages가 null이 아닌 경우에만 처리
	        List<PostImageDTO> postImageDTOList = postImages.stream().map(postImage -> {
	            if (postImage != null) {
	                return PostImageDTO.builder()
	                        .imgName(postImage.getImgName())
	                        .path(postImage.getPath())
	                        .uuid(postImage.getUuid())
	                        .build();
	            } else {
	                return null;
	            }
	        }).collect(Collectors.toList());

	        postDTO.setImageDTOList(postImageDTOList);
	    }

	    postDTO.setCommentCnt(commentCnt);
	    postDTO.setLikeCnt(likeCnt);

	    System.out.println("commentCnt value: " + commentCnt);

	    return postDTO;
	}

	default Map<String, Object> dtoToEntity(PostDTO postDTO) { //Map 타입으로 변환

        Map<String,Object> entityMap = new HashMap<>();
        
        User user = User.builder()
				.uid(postDTO.getU_id()).build();
		
		Board board = Board.builder()
				.bid(postDTO.getB_id()).build();
				
		Post post = Post.builder()
				.user(user)
				.board(board)
				.poid(postDTO.getPo_id())
				.potitle(postDTO.getPo_title())
				.pocontent(postDTO.getPo_content())
				.pohitcount(postDTO.getPo_hitcount())
				.posecret(postDTO.isPo_secret())
				.build();

        entityMap.put("post", post);

        List<PostImageDTO> imageDTOList = postDTO.getImageDTOList();

        //PostImageDTO 처리
        if(imageDTOList != null && imageDTOList.size() > 0){
            List<PostImage> postImageList = imageDTOList.stream().map(fileImageDTO -> {

                PostImage file = PostImage.builder()
                        .path(fileImageDTO.getPath())
                        .imgName(fileImageDTO.getImgName())
                        .uuid(fileImageDTO.getUuid())
                        .post(post)
                        .build();

                return file;
                
            }).collect(Collectors.toList());

            entityMap.put("imgList", postImageList);
        }

        return entityMap;
    }

	
}
