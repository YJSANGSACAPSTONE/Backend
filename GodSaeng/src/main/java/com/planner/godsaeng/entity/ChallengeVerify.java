package com.planner.godsaeng.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//챌린지 참가(mapping table)과 1:N관계인 challengeVerify입니다
@Entity
@Table(name = "godsaeng_challengeverify")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter


//cv - challengeverify의 약자
public class ChallengeVerify {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long cvid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumns({
	        @JoinColumn(name = "cid", referencedColumnName = "cid"),
	        @JoinColumn(name = "uid", referencedColumnName = "uid")
	    })

	private ChallengeParticipate challengeParticipate;
	
	@Column(length=200, nullable=true)
	private String cvphoto;

	@Column(length=50, nullable=true)
	private LocalDateTime cvtime;
	
	@Column(length=50, nullable=true)
	private String cvzepid;

	@Column(nullable = false)
	private int cvsuccessornot;
	
	public void changeSuccessData(int cvsuccessornot) {
		this.cvsuccessornot = cvsuccessornot;
		
	}
	
	

}
