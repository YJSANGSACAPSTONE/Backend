package com.planner.godsaeng.dto;

import com.planner.godsaeng.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
	
	private long kp_id;
	private String kp_methodtype;
	private String kp_date;
	private int kp_amount;
//	private User user;
}
