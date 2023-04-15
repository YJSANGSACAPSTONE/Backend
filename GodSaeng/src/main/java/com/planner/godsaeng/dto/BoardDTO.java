package com.planner.godsaeng.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class BoardDTO {
	private long b_id; 				// 계시글 id
	private String u_id;
	private LocalDateTime b_date;	// 작성시간 
	private String b_title;			// 제목
	private String b_content;		// 내용
	private String b_image; 		// 사진
	private String b_category;		// 카테고리
	private int b_count;			// 조회수
}
