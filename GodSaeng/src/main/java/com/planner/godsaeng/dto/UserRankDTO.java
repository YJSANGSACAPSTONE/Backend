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
public class UserRankDTO {
	
	private String unickname;
	private int ulevel;
	private String uphoto;

}
