package com.planner.godsaeng.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.planner.godsaeng.dto.PageRequestDTO;
import com.planner.godsaeng.dto.PageResultDTO;
import com.planner.godsaeng.dto.PostDTO;
import com.planner.godsaeng.entity.Post;
import com.planner.godsaeng.entity.User;
import com.planner.godsaeng.entity.PostImage;
import com.planner.godsaeng.entity.PostLike;
import com.planner.godsaeng.repository.PostRepository;
import com.planner.godsaeng.repository.UserRepository;
import com.planner.godsaeng.repository.CommentRepository;
import com.planner.godsaeng.repository.PostImageRepository;
import com.planner.godsaeng.repository.PostLikeRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
	
	private final PostRepository postRepository;
	private final PostImageRepository imageRepository;
	private final CommentRepository commentRepository;
	private final PostLikeRepository postLikeRepository;
	private final UserRepository userRepository;
	
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

		return post.getPoid();
	}
	
	@Override
	public PageResultDTO<PostDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
		log.info("getList +++++" + pageRequestDTO);

		Page<Object[]> result = postRepository.searchPage(
				pageRequestDTO.getType(),
				pageRequestDTO.getKeyword(),
				pageRequestDTO.getPageable(Sort.by("poid").descending()));
		
		result.forEach(en -> System.out.println(en[4].getClass().getName() + "<--------en[4]"));
		
		Function<Object[], PostDTO> fn = (en -> entityToDto(
				(Post) en[0],
				null,
				Long.valueOf(en[3].toString()),
				Long.valueOf(en[4].toString()))	
		);

		return new PageResultDTO<>(result, fn);
	}

	@Override
	public PageResultDTO<PostDTO, Object[]> getListByBoard(PageRequestDTO pageRequestDTO, int bid) {
		log.info("getListByBoard +++++" + pageRequestDTO);

		Page<Object[]> result = postRepository.searchPageByBoard (
				pageRequestDTO.getType(),
				pageRequestDTO.getKeyword(),
				pageRequestDTO.getPageable(Sort.by("poid").descending()),
				bid);

		result.forEach(en -> System.out.println(en[1].getClass().getName() + "<--------en[1]"));
		result.forEach(en -> System.out.println(en[4].getClass().getName() + "<--------en[4]"));

		Function<Object[], PostDTO> fn = (en -> entityToDto(
				(Post) en[0],
				null,
				Long.valueOf(en[3].toString()),
				Long.valueOf(en[4].toString()))
		);

		return new PageResultDTO<>(result, fn);
	}
	
	@Override
	public PostDTO getPost(Long poid) {
	    List<Object[]> result = postRepository.getPostWithAll(poid);

	    Post post = (Post) result.get(0)[0];
	    List<PostImage> postImageList = new ArrayList<>();
	    result.forEach(arr -> {
	        PostImage postImage = (PostImage) arr[1];
	        postImageList.add(postImage);
	    });

	    User user = (User) result.get(0)[2];
	    Long commentCnt = (Long) result.get(0)[3];
	    Long likeCnt = (Long) result.get(0)[4];

	    PostDTO postDTO = entityToDto(post, postImageList, commentCnt, likeCnt);

	    viewCountValidation(postDTO);

	    return postDTO;
	}

	public void viewCountValidation(PostDTO postDTO) {
	    Long po_id = postDTO.getPo_id();
	    Post post = postRepository.getOne(postDTO.getPo_id());
	    
	    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	    HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();

	    Cookie[] cookies = request.getCookies();
	    Cookie cookie = null;
	    boolean isCookie = false;

	    // request에 쿠키들이 있을 때
	    for (int i = 0; cookies != null && i < cookies.length; i++) {
	    	// postView 쿠키가 있을 때
	        if (cookies[i].getName().equals("postView")) {
	        	// cookie 변수에 저장
	            cookie = cookies[i];
	            // 만약 cookie 값에 현재 게시글 번호가 없을 때
	            if (!cookie.getValue().contains("[" + po_id + "]")) {
	            	// 해당 게시글 조회수를 증가시키고, 쿠키 값에 해당 게시글 번호를 추가
	            	post.addHitCount();
	                cookie.setValue(cookie.getValue() + "[" + po_id + "]");
	            }
	            isCookie = true;
	            break;
	        }
	    }

	    // 만약 postView라는 쿠키가 없으면 처음 접속한 것이므로 새로 생성
	    if (!isCookie) {
		    post.addHitCount();
	        cookie = new Cookie("postView", "[" + po_id + "]");
	    }

	    // 쿠키 유지시간을 오늘 하루 자정까지로 설정
	    long todayEndSecond = LocalDate.now().atTime(LocalTime.MAX).toEpochSecond(ZoneOffset.UTC);
	    long currentSecond = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
	    cookie.setPath("/"); // 모든 경로에서 접근 가능
	    cookie.setMaxAge((int) (todayEndSecond - currentSecond));
	    response.addCookie(cookie);

	    // 조회수 저장
	    postRepository.save(post);   
	}
	
	@Transactional
	@Override
	public void removeWithAll(Long poid) {
		postRepository.deleteById(poid);
		imageRepository.deleteByPoid(poid);
		commentRepository.deleteByPoid(poid);
		postLikeRepository.deleteByPoid(poid);
	}

	@Transactional
	@Override
	public void modify(PostDTO postDTO) {
		Post post = postRepository.getOne(postDTO.getPo_id());
		
		post.changeTitle(postDTO.getPo_title());
		post.changeContent(postDTO.getPo_content());
		
		System.out.println("post-------------" + post);
		
		postRepository.save(post);
	}
	
	@Override
	public boolean likePost(Long poid, String uid) {
	    Optional<Post> optionalPost = postRepository.findById(poid);
	    if (optionalPost.isPresent()) {
	        Post post = optionalPost.get();
	        
	        User user = userRepository.findByUid(uid).orElseThrow(() ->
	            new RuntimeException("User not found with ID: " + uid)
	        );
	        
	        Optional<PostLike> optionalPostLike = postLikeRepository.findByPostAndUser(post, user);
	        if (optionalPostLike.isPresent()) {
	            // 이미 좋아요한 경우, 좋아요 취소
	            postLikeRepository.delete(optionalPostLike.get());
	            return false; // 좋아요 취소된 상태
	        } else {
	            // 좋아요 처리
	            PostLike postLike = new PostLike(post, user);
	            postLikeRepository.save(postLike);
	            return true; // 좋아요 처리된 상태
	        }
	    } else {
	        // 게시물이 존재하지 않을 때 처리
	        throw new RuntimeException("Post not found with ID: " + poid);
	    }
	}
	
	@Override
	public boolean isPostLikedByUser(Long poid, String uid) {
	    Optional<Post> optionalPost = postRepository.findById(poid);
	    if (optionalPost.isPresent()) {
	        Post post = optionalPost.get();
	        
	        User user = userRepository.findByUid(uid).orElseThrow(() ->
	            new RuntimeException("User not found with ID: " + uid)
	        );
	        
	        return postLikeRepository.existsByPostAndUser(post, user);
	    } else {
	        // 게시물이 존재하지 않을 때 처리
	        throw new RuntimeException("Post not found with ID: " + poid);
	    }
	}
	
	@Override
    public List<Post> getPopularPosts(int limit) {
        // 좋아요 수가 많은 순으로 인기 게시물 조회
        List<Post> popularPosts = postRepository.getPopularPosts(limit);

        return popularPosts;
    }


}