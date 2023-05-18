package com.planner.godsaeng.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@ToString(exclude = {"user", "board"})
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class Post extends BaseEntity {
	
	@ManyToOne(fetch = FetchType.LAZY) 
	private User user;				// 유저 ID
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Board board;				// 게시판 ID
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long poid;				// 게시글 ID
	
	@Column(length = 100, nullable = false)
	private String potitle;			// 제목
	
	@Column(length = 1500, nullable = false)
	private String pocontent;		// 내용
	
	@Column(columnDefinition = "int default 0")
	private int pohitcount;			// 조회수
	
//	@Column(columnDefinition = "int default 0")
//	private int polike;				// 추천수 // 수정 필요
	
	@Column(columnDefinition = "boolean default true")
	private boolean posecret;		// 비밀글 여부
	
	public void changeTitle(String potitle) {
		this.potitle = potitle;
	}
	
	public void changeContent(String pocontent) {
		this.pocontent = pocontent;
	}

	public void setPostHitCount(int pohitcount) {
        this.pohitcount = pohitcount;
    }
	
	
	
}
