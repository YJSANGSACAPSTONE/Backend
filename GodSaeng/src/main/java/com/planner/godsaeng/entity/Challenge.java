package com.planner.godsaeng.entity;

import java.sql.Date;

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
import lombok.ToString;

@Entity
@Table(name = "godsaeng_challenge")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Challenge {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long c_id;
	
	@Column(length=30, nullable = false)
	private String c_name;
	
	@Column(length=500, nullable = false)
	private String c_content;
	
	@Column(length=30, nullable = false)
	private Date c_startdate;
	
	@Column(length=30, nullable = false)
	private Date c_enddate;
	
	@Column(nullable = false)
	private int c_numberofparticipants;
	
	@Column(nullable = false)
	private int c_category;
	
	@Column(length = 100, nullable = false)
	private String c_thumbnails;
	
	@Column(length = 50, nullable = false)
	private String c_introduction;
	
	@Column(nullable = true)
	private int c_fee;
	
	@Column(nullable = false)
	private int c_numberofphoto;
	
	@Column(nullable = false)
	private int c_typeofverify;
	
	@Column(nullable = false)
	private int c_typeoffrequency;
	
	@Column(nullable= false)
	private int c_frequency;
	
	@Column(nullable = false)
	private int c_score;
}
