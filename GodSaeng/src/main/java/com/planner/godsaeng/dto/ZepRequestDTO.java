package com.planner.godsaeng.dto;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ZepRequestDTO {
	
	
	private Long cid;
	private String cvzepid;
	private LocalDateTime cvtime;
	private Integer cvsuccessornot;

}
