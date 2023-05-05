package com.planner.godsaeng.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	PostDTO getPost(Long pid);
	
	// 게시물 삭제
	void removeWithImages(Long pid);
	
	
	// 게시물 수정
	void modify(PostDTO postDTO);

//	default Post dtoToEntiy(PostDTO dto) {
//		User user = User.builder()
//				.uid(dto.getU_id()).build();
//		
//		Board board = Board.builder()
//				.bid(dto.getB_id()).build();
//				
//		Post post = Post.builder()
//				.uid(user)
//				.bid(board)
//				.pid(dto.getP_id())
//				.ptitle(dto.getP_title())
//				.pcontent(dto.getP_content())
//				.phitCount(dto.getP_hitCount())
//				.plike(dto.getP_like())
//				.psecret(dto.isP_secret())
//				.build();
//		return post;
//	}

	default PostDTO entityToDto(Post post, User user, List<PostImage> postImages) {
		PostDTO postDTO = PostDTO.builder()
				.u_id(user.getUid())
//				.u_id(post.getUser().getUid())
				.b_id(post.getBid().getBid())
				.p_id(post.getPid())
				.p_title(post.getPtitle())
				.p_content(post.getPcontent())
				.p_regDate(post.getRegDateTime())
				.p_modDate(post.getMoDateTime())
				.p_hitCount(post.getPhitCount())
				.p_like(post.getPlike())
				.p_secret(post.isPsecret())
				.build();
		
		List<PostImageDTO> postImageDTOList = postImages.stream().map(postImage -> { 
			return PostImageDTO.builder()
					.imgName(postImage.getImgName())
					.path(postImage.getPath())
					.uuid(postImage.getUuid())
					.build();
		}).collect(Collectors.toList());

		postDTO.setImageDTOList(postImageDTOList);
		
		return postDTO;
	}

	default Map<String, Object> dtoToEntity(PostDTO postDTO) { //Map 타입으로 변환

        Map<String,Object> entityMap = new HashMap<>();
        
        User user = User.builder()
				.uid(postDTO.getU_id()).build();
		
		Board board = Board.builder()
				.bid(postDTO.getB_id()).build();
				
		Post post = Post.builder()
				.uid(user)
				.bid(board)
				.pid(postDTO.getP_id())
				.ptitle(postDTO.getP_title())
				.pcontent(postDTO.getP_content())
				.phitCount(postDTO.getP_hitCount())
				.plike(postDTO.getP_like())
				.psecret(postDTO.isP_secret())
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
