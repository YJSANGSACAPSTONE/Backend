package com.planner.godsaeng.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="godsaeng_challengeparticipate")
@ToString(exclude="challenge")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

//매핑테이블입니다(챌린지와 유저 관계(참가))
//cp = challengeparticipant(챌린지참가)
public class ChallengeParticipate {
	@EmbeddedId
	private ChallengeParticipateId challengeParticipateId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cid")
	@MapsId("cid")
	private Challenge challenge;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid")
	@MapsId("uid")
	private User user;
	
	@Column
	private int cpfinalsuccess;
	
	@OneToMany(mappedBy="challengeParticipate",cascade = javax.persistence.CascadeType.ALL, orphanRemoval = true)
	private List<ChallengeVerify>verify = new ArrayList<>();
	public Long getCid() {
        return challenge.getCid(); // Assuming the Challenge entity has an 'id' property
    }
	public String getUid() {
		return user.getUid();
	}
	

	
}
