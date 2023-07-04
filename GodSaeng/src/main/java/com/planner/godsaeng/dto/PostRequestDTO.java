package com.planner.godsaeng.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostRequestDTO {

	private int b_id; 							// 게시판 id
	private String u_id;						// 유저 id
	private Long po_id;							// 계시글 id
	private String po_title;						// 제목
	private String po_content;					// 내용
	private boolean po_secret;					// 비밀글 여부
	
//    private String[] imageLists = new String[];


	
}
