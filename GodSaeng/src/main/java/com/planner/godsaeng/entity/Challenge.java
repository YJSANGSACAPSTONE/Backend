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
	private long cid;
	
	@Column(length=30, nullable = false)
	private String cname;
	
	@Column(length=500, nullable = false)
	private String ccontent;
	
	@Column(length=30, nullable = false)
	private Date cstartdate;
	
	@Column(length=30, nullable = false)
	private Date cenddate;

	@Column(nullable = false)
	private int cnumberofparticipants;
	
	@Column(nullable = false)
	private int ccategory;
	
	@Column(length = 100, nullable = false)
	private String cthumbnails;
	
	@Column(length = 50, nullable = false)
	private String cintroduction;
	
	@Column(nullable = true)
	private int cfee;
	
	@Column(nullable = false)
	private int cnumberofphoto;
	
	@Column(nullable = false)
	private int ctypeofverify;
	
	@Column(nullable = false)
	private int ctypeoffrequency;
	
	@Column(nullable= false)
	private int cfrequency;
	
	@Column(nullable = false)
	private int cscore;
}
