package com.planner.godsaeng.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChallengeParticipateDTO {
	
	private Long cpid;
	private ChallengeDTO challenge;
	private UserDTO user;
	private int cpfinalsuccess;
}
