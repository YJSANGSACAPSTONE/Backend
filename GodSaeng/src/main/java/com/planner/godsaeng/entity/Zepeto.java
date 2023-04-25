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
@Table(name="godsaeng_zepeto")
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Zepeto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long z_id;
	
	@Column(length=30, nullable=false)
	private String name;
	
	@Column(length=30, nullable = false)
	private String challenge;
	
	
	
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
