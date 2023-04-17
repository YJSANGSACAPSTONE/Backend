package com.planner.godsaeng.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class UserDTO {
   private String u_id;
   private String u_nickname;
   private String u_zepetoid;
   private String u_img;
   private int u_deposit;
   private String u_grade;
   private String u_email;
   private String u_successedchallenge;
}