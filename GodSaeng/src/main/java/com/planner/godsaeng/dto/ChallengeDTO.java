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
	private MultipartFile thumbnail;
    private String c_thumbnails;
	
	public void toEntity(ChallengeDTO d) {
		Challenge entity = Challenge.builder()
				.cid(d.getC_id())
				.cname(d.getC_name())
				.ccontent(d.getC_content())
				.cstartdate(d.getC_startdate())
				.cenddate(d.getC_enddate())
				.cnumberofparticipants(d.getC_numberofparticipants())
				.ccategory(d.getC_category())
				.cintroduction(d.getC_introduction())
				.cfee(d.getC_fee())
				.cnumberofphoto(d.getC_numberofphoto())
				.ctypeofverify(d.getC_typeofverify())
				.ctypeoffrequency(d.getC_typeoffrequency())
				.cfrequency(d.getC_frequency())
				.cscore(d.getC_score())
				.build();
		
	}
}


