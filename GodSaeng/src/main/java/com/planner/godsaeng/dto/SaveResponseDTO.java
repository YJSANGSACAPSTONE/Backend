package com.planner.godsaeng.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SaveResponseDTO {

	private String u_id;
	private String u_nickname;
	private String u_zepid;
	private String u_content;
}
