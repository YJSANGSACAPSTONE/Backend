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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="godsaeng_usagehistory")
@ToString
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsageHistory {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long uhid;
	
	@Column(length=30, nullable=false)
	private int uhamount;
	
	@Column(length=30, nullable=false)
	private String uhdate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid", nullable = false)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cid", nullable = false)
	private Challenge challenge;
	
}
