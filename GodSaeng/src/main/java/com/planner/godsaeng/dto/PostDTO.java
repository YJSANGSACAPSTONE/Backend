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
	private Long po_id;							// 계시글 id
	private LocalDateTime po_regDate, po_modDate;	// 작성 시간, 수정 시간
	private String po_title;						// 제목
	private String po_content;					// 내용
	private int po_hitcount;						// 조회수
	private int po_like;							// 추천수
	private boolean po_secret;					// 비밀글 여부
	
	@Builder.Default
    private List<PostImageDTO> imageDTOList = new ArrayList<>();

	// 댓글 수 jpa의 count()
    private Long commentCnt;
	
}
