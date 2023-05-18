package com.planner.godsaeng.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="godsaeng_board")
@ToString
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bid;				// 계시판 ID
	private String bname;			// 게시판 이름
	
	private String aid;				// 유저 ID
	
	


}