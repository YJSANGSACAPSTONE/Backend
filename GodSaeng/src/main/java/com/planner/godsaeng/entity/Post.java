package com.planner.godsaeng.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="godsaeng_post")
@ToString(exclude = {"uid", "bid"})
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class Post extends BaseEntity {
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User uid;				// 유저 ID
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Board bid;				// 게시판 ID
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pid;				// 게시글 ID
	
	@Column(length = 100, nullable = false)
	private String ptitle;			// 제목
	
	@Column(length = 1500, nullable = false)
	private String pcontent;		// 내용
	
	@Column(columnDefinition = "int default 0")
	private int phitCount;			// 조회수
	
	@Column(columnDefinition = "int default 0")
	private int plike;				// 추천수
	
	@Column(columnDefinition = "boolean default true")
	private boolean psecret;		// 비밀글 여부
	
	public void changeTitle(String ptitle) {
		this.ptitle = ptitle;
	}
	
	public void changeContent(String pcontent) {
		this.pcontent = pcontent;
	}
	
	
	
}
