package com.planner.godsaeng.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="godsaeng_comment")
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Comment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long c_id;
	
	@Column(length=30, nullable=false)
	private String c_writer;
	
	@Column(length=30, nullable = false)
	private String c_content;
	
	@Column(length=30, nullable = false)
	private String c_time;
	
	

	
//	@Column(name = "created_date")
//	@CreatedDate
//	private String createdDate;
//	
	
//	@ManyToOne
//	@JoinColumn(name = "posts_id")
//	private Posts posts;
//	
//	@ManyToOne
//	@JoinColumn( name = "user_id")
//	private User user;

	
}
