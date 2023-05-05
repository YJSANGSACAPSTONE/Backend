package com.planner.godsaeng.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class PostDTO {
	private int b_id; 							// 게시판 id
	private String u_id;						// 유저 id
	private Long p_id;							// 계시글 id
	private LocalDateTime p_regDate, p_modDate;	// 작성 시간, 수정 시간
	private String p_title;						// 제목
	private String p_content;					// 내용
	private int p_hitCount;						// 조회수
	private int p_like;							// 추천수
	private boolean p_secret;					// 비밀글 여부
	
	@Builder.Default
    private List<PostImageDTO> imageDTOList = new ArrayList<>();

	
}
