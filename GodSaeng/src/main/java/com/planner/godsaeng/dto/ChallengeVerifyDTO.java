package com.planner.godsaeng.dto;

import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class ChallengeVerifyDTO {
	private long cvid;
	private ChallengeParticipateDTO ChallengeParticipate;
	private MultipartFile verifyPhoto;
	private String cvphoto;
	private LocalDateTime cvtime;
	private int cvsuccessornot;
}
