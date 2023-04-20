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
@Table(name="godsaeng_challengeparticipate")
@ToString
@Getter



//매핑테이블입니다(챌린지와 유저 관계(참가))
//cp = challengeparticipant(챌린지참가)
public class ChallengeParticipate {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long cpid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cid")
	private Challenge challenge;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "u_id")
	private User user;
	
	@Column
	private int cpfinalsuccess;
	
	public ChallengeParticipate() {}
	
	
	public ChallengeParticipate(Challenge challenge, User user) {
		this.challenge = challenge;
		this.user = user;
	}
	
}
