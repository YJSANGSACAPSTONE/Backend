package com.planner.godsaeng.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Builder
@Setter
@Getter
@AllArgsConstructor
public class ZepIdVerifyViewDTO {
	private String uzepid;
	private int uverifiedornot;

}
