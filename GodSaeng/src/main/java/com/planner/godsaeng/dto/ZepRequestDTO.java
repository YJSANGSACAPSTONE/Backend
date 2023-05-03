package com.planner.godsaeng.dto;

import java.sql.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ZepRequestDTO {
	
	
	private Long cid;
	private String cvzepid;
	private Date cvtime;
	private Integer cvsuccessornot;

}
