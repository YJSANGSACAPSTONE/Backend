package com.planner.godsaeng.dto;

import java.math.BigInteger;
import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import com.planner.godsaeng.entity.Challenge;

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

public class ChallengeDTO {
	

	private Long c_id;
	private String c_name;
	private String c_content;
	private Date c_startdate;
	private Date c_enddate;
	private Integer c_numberofparticipants;
	private Integer c_category;
	private String c_introduction;
	private Integer c_fee;
	private Integer c_numberofphoto;
	private Integer c_typeofverify;
	private Integer c_typeoffrequency;
	private Integer c_frequency;
	private Integer c_score;
	private MultipartFile thumbnailData;
    private String c_thumbnails;
}


