package com.planner.godsaeng.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.planner.godsaeng.dto.PageRequestDTO;
import com.planner.godsaeng.dto.PageResultDTO;
import com.planner.godsaeng.dto.PostDTO;
import com.planner.godsaeng.dto.PostImageDTO;
import com.planner.godsaeng.dto.UserDTO;
import com.planner.godsaeng.entity.Board;
import com.planner.godsaeng.entity.Post;
import com.planner.godsaeng.entity.PostImage;
import com.planner.godsaeng.entity.User;

public interface PostService {
	Long register(PostDTO postDTO);
	
	// 목록처리
	PageResultDTO<PostDTO, Object[]> getList(PageRequestDTO pageRequestDTO);
	
	// 게시물 조회
	PostDTO getPost(Long poid);
	
	// 게시물 삭제
	void removeWithAll(Long poid);
	
	// 게시물 수정
	void modify(PostDTO postDTO);
	
	// 조회수 처리
	void viewCountValidation(PostDTO postDTO, HttpServletRequest request, HttpServletResponse response);
    
	default PostDTO entityToDto(Post post, User user, List<PostImage> postImages, Long commentCnt) {
		PostDTO postDTO = PostDTO.builder()
				.u_id(user.getUid())
				.b_id(post.getBoard().getBid())
				.po_id(post.getPoid())
				.po_title(post.getPotitle())
				.po_content(post.getPocontent())
				.po_regDate(post.getRegDateTime())
				.po_modDate(post.getModDateTime())
				.po_hitcount(post.getPohitcount())
//				.po_like(post.getPolike())
				.po_secret(post.isPosecret())
				.build();
		
		List<PostImageDTO> postImageDTOList = postImages.stream().map(postImage -> { 
			return PostImageDTO.builder()
					.imgName(postImage.getImgName())
					.path(postImage.getPath())
					.uuid(postImage.getUuid())
					.build();
		}).collect(Collectors.toList());

		postDTO.setImageDTOList(postImageDTOList);
		postDTO.setCommentCnt(commentCnt);
		
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
//				.polike(postDTO.getPo_like())
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
