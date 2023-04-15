package com.planner.godsaeng.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="godsaeng_board")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long b_id;
	
	@Column(length=20, nullable=false)
	private String u_id;
	
	@Column(length=30, nullable=false)
	private LocalDateTime b_date;	// 작성시간
	
	@Column(length=30, nullable=false)
	private String b_title;			// 제목

	@Column(length=500, nullable=true)
	private String b_content;		// 내용
	
	@Column(length=30, nullable=true)
	private String b_image; 		// 사진
	
	@Column(length=30, nullable=false)
	private String b_category;		// 카테고리
	
	@Column(length=30, nullable=false)
	private int b_count;			// 조회수


}