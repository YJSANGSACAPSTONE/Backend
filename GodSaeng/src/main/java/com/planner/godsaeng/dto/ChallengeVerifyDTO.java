package com.planner.godsaeng.dto;

import java.sql.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class ChallengeVerifyDTO {
	private long cvid;
	private ChallengeParticipateDTO ChallengeParticipate;
	private String cvphoto;
	private String cvzepid;
	private Date cvtime;
	private int cvsuccessornot;
}
