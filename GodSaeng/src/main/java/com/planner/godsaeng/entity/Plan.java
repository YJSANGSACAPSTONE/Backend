package com.planner.godsaeng.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="godsaeng_plan")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class Plan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pid;
	
	@Column(length=20, nullable=false)
	private String uid;
	
	@Column(length=30, nullable=false)
	private LocalDate pstartdate;
	
	@Column(length=30, nullable=true)
	private LocalDate penddate;
	
	@Column(length=30, nullable=false)
	private LocalDateTime pstarttime;
	
	@Column(length=30, nullable=true)
	private LocalDateTime pendtime;
	
	@Column(length=30, nullable=false)
	private String ptitle;
	
	@Column(length=500, nullable=true)
	private String pcontent;
	
	@Column(length=30, nullable=true)
	private String pcategory;
	
	@Column(length=30, nullable=true)
	private Integer premindornot;
	
	
	

}
