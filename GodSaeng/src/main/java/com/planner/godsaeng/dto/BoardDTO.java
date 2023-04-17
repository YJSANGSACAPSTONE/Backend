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
	private int b_number;			// 게시글 번호
	private String u_id;			// 작성자 id
	private LocalDateTime b_date;	// 작성시간 
	private String b_title;			// 제목
	private String b_content;		// 내용
	private String b_image; 		// 사진
	private String b_category;		// 카테고리
	private int b_count;			// 조회수
	
	@Override
	public String toString() {
		return "BoardDTO [b_id=" + b_id + ", b_number=" + b_number + ", u_id=" + u_id + ", b_date=" + b_date
				+ ", b_title=" + b_title + ", b_content=" + b_content + ", b_image=" + b_image + ", b_category="
				+ b_category + ", b_count=" + b_count + "]";
	}
	
	
}
