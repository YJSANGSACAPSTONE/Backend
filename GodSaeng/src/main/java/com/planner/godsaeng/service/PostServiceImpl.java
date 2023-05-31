package com.planner.godsaeng.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
	    
	    Post post = (Post) result.get(0)[0];    // Post 엔티티는 가장 앞에 존재 - 모든 Row가 동일한 값이다

	    // 조회수 업데이트
	    post.setPostHitCount(post.getPohitcount() + 1);

	    List<PostImage> postImageList = new ArrayList<>();    // 게시글의 이미지개수만큼 PostImage 객체 필요

	    result.forEach(arr -> {
	        PostImage postImage = (PostImage) arr[1];
	        postImageList.add(postImage);
	    });

	    User user = (User) result.get(0)[2];

	    Long commentCnt = (Long) result.get(0)[3]; // 댓글 개수 - 모든 Row가 동일한 값
	    
	    Long likeCnt = (Long) result.get(0)[4]; // 좋아요 개수 - 모든 Row가 동일한 값

	    PostDTO postDTO = entityToDto(post, postImageList, commentCnt, likeCnt);

	    // viewCountValidation 로직
	    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	    HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
	    viewCountValidation(postDTO, request, response);
	    
	    // DB에 조회수 업데이트 반영
	    postRepository.save(post);
	    
	    return postDTO;
	}
	
	public void viewCountValidation(PostDTO postDTO, HttpServletRequest request, HttpServletResponse response) {
	    // PostDTO에서 필요한 정보 가져오기
	    Long poid = postDTO.getPo_id();
	    int postHitCount = postDTO.getPo_hitcount();

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
	            if (!containsPoid(cookie.getValue(), poid)) {
	                // 해당 게시글 조회수를 증가시키고, 쿠키 값에 해당 게시글 번호를 추가
	                postHitCount++;
	                cookie.setValue(cookie.getValue() + "[" + poid + "]");
	            }
	            isCookie = true;
	            break;
	        }
	    }
	    // 만약 postView라는 쿠키가 없으면 처음 접속한 것이므로 새로 생성
	    if (!isCookie) {
	        postHitCount++;
	        cookie = new Cookie("postView", "[" + poid + "]");
	    }

	    // 쿠키 유지시간을 오늘 하루 자정까지로 설정
	    LocalDateTime todayEnd = LocalDate.now().atTime(LocalTime.MAX);
	    Duration duration = Duration.between(LocalDateTime.now(), todayEnd);
	    long secondsUntilTodayEnd = duration.getSeconds();
	    cookie.setMaxAge((int) secondsUntilTodayEnd);
	    cookie.setPath("/"); // 모든 경로에서 접근 가능
	    response.addCookie(cookie);

	    // 조회수 업데이트
	    postDTO.setPo_hitcount(postHitCount);
	}
	
	private boolean containsPoid(String cookieValue, Long poid) {
	    String[] values = cookieValue.split("\\[|\\]");
	    for (String value : values) {
	        if (value.equals(poid.toString())) {
	            return true;
	        }
	    }
	    return false;
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
	public void likePost(Long poid, String uid) {
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
	        } else {
	            // 좋아요 처리
	            PostLike postLike = new PostLike(post, user);
	            postLikeRepository.save(postLike);
	        }
	    } else {
	        // 게시물이 존재하지 않을 때 처리
	        // 처리 방법에 따라 변경 가능
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
