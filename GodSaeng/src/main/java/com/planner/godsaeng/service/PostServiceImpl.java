package com.planner.godsaeng.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.planner.godsaeng.dto.PageRequestDTO;
import com.planner.godsaeng.dto.PageResultDTO;
import com.planner.godsaeng.dto.PostDTO;
import com.planner.godsaeng.entity.Board;
import com.planner.godsaeng.entity.Post;
import com.planner.godsaeng.entity.User;
import com.planner.godsaeng.entity.PostImage;
import com.planner.godsaeng.repository.PostRepository;
import com.planner.godsaeng.repository.FileRepositroy;
import com.planner.godsaeng.repository.PostImageRepository;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
	private final PostRepository postRepository;
	private final PostImageRepository imageRepository; 

	@Transactional
	@Override
	public Long register(PostDTO postDTO) {
		log.info("---PostServiceImpl register()" + postDTO);
		
		Map<String, Object> entityMap = dtoToEntity(postDTO);
		Post post = (Post) entityMap.get("post");
		List<PostImage> postImageList = (List<PostImage>) entityMap.get("imgList");

		postRepository.save(post);
		if (postImageList != null) {
			postImageList.forEach(postImage -> {
				imageRepository.save(postImage);
			});
		}

		return post.getPid();
	}

	@Override
	public PageResultDTO<PostDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
		log.info("getList +++++" + pageRequestDTO);

		Page<Object[]> result = postRepository.searchPage(
				pageRequestDTO.getType(),
				pageRequestDTO.getKeyword(),
				pageRequestDTO.getPageable(Sort.by("pid").descending()));
				
		result.forEach(en -> System.out.println(en[2].getClass().getName()));
		
		Function<Object[], PostDTO> fn = (en -> entityToDto(
				(Post)en[0],
				(User)en[1],
				(List<PostImage>) (Arrays.asList((PostImage) en[2]))
				
		));

		return new PageResultDTO<>(result, fn);
	}

	@Override
	public PostDTO getPost(Long pid) {
		List<Object[]> result = postRepository.getPostWithAll(pid);
		
		Post post = (Post) result.get(0)[0];	// Post 엔티티는 가장 앞에 존재 - 모든 Row가 동일한 값이다
		
		List<PostImage> postImageList = new ArrayList<>();	// 게시글의 이미지개수만큼 PostImage 객체 필요
		
		result.forEach(arr -> {
			PostImage postImage = (PostImage) arr[1];
			postImageList.add(postImage);
		});
		
		User user = (User) result.get(0)[2];
		
		return entityToDto(post, user, postImageList);
	}
	
	@Transactional
	@Override
	public void removeWithImages(Long pid) {
		postRepository.deleteById(pid);
		imageRepository.deleteByBid(pid);
	}

	@Transactional
	@Override
	public void modify(PostDTO postDTO) {
		Post post = postRepository.getOne(postDTO.getP_id());
		
		post.changeTitle(postDTO.getP_title());
		post.changeContent(postDTO.getP_content());
		
		System.out.println("post-------------" + post);
		
		postRepository.save(post);
	}

}
