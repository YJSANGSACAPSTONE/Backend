package com.planner.godsaeng.service;

import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.planner.godsaeng.dto.PageRequestDTO;
import com.planner.godsaeng.dto.PageResultDTO;
import com.planner.godsaeng.dto.PostDTO;
import com.planner.godsaeng.entity.Board;
import com.planner.godsaeng.entity.Post;
import com.planner.godsaeng.entity.User;
import com.planner.godsaeng.repository.PostRepository;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
	private final PostRepository postRepository;

	@Override
	public Long register(PostDTO dto) {
		log.info("---PostServiceImpl register()" + dto);

		Post post = dtoToEntiy(dto);

		postRepository.save(post);

		return post.getPid();
	}

	@Override
	public PageResultDTO<PostDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
		log.info(pageRequestDTO);

		Function<Object[], PostDTO> fn = (en -> entityToDto((Post)en[0], (User)en[1]));

		Page<Object[]> result = postRepository.searchPage(
				pageRequestDTO.getType(),
				pageRequestDTO.getKeyword(),
				pageRequestDTO.getPageable(Sort.by("pid").descending()));
				
		result.forEach(en -> System.out.println(en));

		return new PageResultDTO<>(result, fn);
	}

	@Override
	public PostDTO get(Long pid) {
		Object result = postRepository.getPostWithUid(pid);
		Object[] arr = (Object[]) result;
		
		return entityToDto((Post)arr[0], (User)arr[1]);
	}

	@Override
	public void removeWithReplies(Long pid) {
		postRepository.deleteById(pid);
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
