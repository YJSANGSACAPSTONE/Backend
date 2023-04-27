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
	private ChallengeDTO challenge;
	private UserDTO user;
	private String cvphoto;
	private Date cvtime;
	private int cvsuccessornot;
	

}
