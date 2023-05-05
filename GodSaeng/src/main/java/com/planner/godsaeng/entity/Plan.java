package com.planner.godsaeng.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="godsaeng_plan")
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert


public class Plan extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid")
	private User user;
	
	@Column(length=30, nullable=false)
	private LocalDate pstartdate;
	
	@Column(length=30, nullable=true)
	private LocalDate penddate;
	
	@DateTimeFormat(pattern = "hh:mm")
	@Column(length=30, nullable=false)
	private LocalTime pstarttime;
	
	@DateTimeFormat(pattern = "hh:mm")
	@Column(length=30, nullable=true)
	private LocalTime pendtime;
	
	@Column(length=30, nullable=false)	
	private String ptitle;
	
	@Column(length=500, nullable=true)
	private String pcontent;
	
	@Column(length=30, nullable=true)
	private String pcategory;
	
	@Column(length=30, nullable=true)
	private Integer premindornot;
	
}