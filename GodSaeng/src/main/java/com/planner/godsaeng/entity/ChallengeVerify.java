package com.planner.godsaeng.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//유저와 챌린지 사이의 관계(인증) 테이블입니다.
@Entity
@Table(name = "godsaeng_challengeverify")
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

//cv - challengeverify의 약자
public class ChallengeVerify {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long cvid;
	
	@ManyToOne
	@JoinColumn(name = "c_id")
	private Challenge challenge;
	
	@ManyToOne
	@JoinColumn(name = "u_id")
	private User user;
	
	@Column(length=200, nullable=true)
	private String cvphoto;
	
	@Column(length=50, nullable=false)
	private Date cvtime;

	@Column(nullable = false)
	private int cvsuccessornot;

}
