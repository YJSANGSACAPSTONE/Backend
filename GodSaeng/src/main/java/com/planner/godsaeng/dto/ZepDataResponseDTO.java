package com.planner.godsaeng.dto;

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
public class ZepDataResponseDTO {
	
	private String uid;
	private Integer cvCount;
	private String uverifiedtime;

}
