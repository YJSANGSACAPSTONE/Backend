package com.planner.godsaeng.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ChallengeParticipateId implements Serializable {
    private static final long serialVersionUID = 1L;
	private Long cid;
	private String uid;
	
	public ChallengeParticipateId(String uid, Long cid) {
	    this.uid = uid;
	    this.cid = cid;
	}

}
