package com.planner.godsaeng.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ChallengeDataDTO {
	

	private Long c_id;
	private String u_id;
	private String c_contents;
}