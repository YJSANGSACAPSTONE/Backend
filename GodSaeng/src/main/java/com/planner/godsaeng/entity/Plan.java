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
@Table(name="godsaeng_plan")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Plan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long p_id;
	
	@Column(length=20, nullable=false)
	private String u_id;
	
	@Column(length=30, nullable=false)
	private Date p_startdate;
	
	@Column(length=30, nullable=false)
	private Date p_enddate;
	
	@Column(length=30, nullable=false)
	private LocalDateTime p_starttime;
	
	@Column(length=30, nullable=false)
	private LocalDateTime p_endtime;
	
	@Column(length=30, nullable=false)
	private String p_title;
	
	@Column(length=500, nullable=true)
	private String p_content;
	
	@Column(length=30, nullable=true)
	private String p_category;
	
	@Column(length=30, columnDefinition = "int default 1")
	private int p_remindornot;
	
	
	

}
