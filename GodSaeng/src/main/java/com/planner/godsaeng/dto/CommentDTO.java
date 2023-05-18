package com.planner.godsaeng.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
	
	// comment ID
	private Long comm_id;
	
	// 게시판 id
	private Long po_id;
	
	// 유저 id
	private String u_id;
	
	private String comm_text;
	
	private LocalDateTime regDate, modDate;
}

