package com.planner.godsaeng.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ChallengeParticipateId implements Serializable {
    private static final long serialVersionUID = 1L;
	private Long cid;
	private String uid;
	
	public ChallengeParticipateId() {
	    // 기본 생성자 내용 추가 (필요한 경우)
	}
	
	public ChallengeParticipateId(String uid, Long cid) {
	    this.uid = uid;
	    this.cid = cid;
	}

}
