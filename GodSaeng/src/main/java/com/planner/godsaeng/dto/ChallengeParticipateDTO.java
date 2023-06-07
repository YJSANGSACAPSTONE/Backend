package com.planner.godsaeng.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChallengeParticipateDTO {
	
	private Long cpid;
	private Long cid;
	private String uid;
	private int cpfinalsuccess;
}