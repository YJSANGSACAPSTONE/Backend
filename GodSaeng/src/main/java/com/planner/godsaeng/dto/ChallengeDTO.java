package com.planner.godsaeng.dto;

import java.sql.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChallengeDTO {
	
	private long c_id;
	private String c_name;
	private String c_content;
	private Date c_startdate;
	private Date c_enddate;
	private int c_numberofparticipants;
	private int c_category;
	private String c_thumbnails;
	private String c_introduction;
	private int c_fee;
	private int c_numberofphoto;
	private int c_typeofverify;
	private int c_typeoffrequency;
	private int c_frequency;
	private int c_score;

}
