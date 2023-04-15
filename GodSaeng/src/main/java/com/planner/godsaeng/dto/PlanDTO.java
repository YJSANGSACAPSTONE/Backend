package com.planner.godsaeng.dto;

import java.sql.Date;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class PlanDTO {
	
	private long p_id;
	private String u_id;
	private Date p_startdate;
	private Date p_enddate;
	private LocalDateTime p_starttime;
	private LocalDateTime p_endtime;
	private String p_title;
	private String p_content;
	private String p_category;
	private int p_remindornot;

}
