package com.planner.godsaeng.dto;

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
public class UserDTO {
   private String u_id;
   private String role;
   private String u_nickname;
   private String u_zepid;
   private int u_deposit;
   private int u_level;
   private String u_content;
   private String u_successedchallenge;
   private String profile_image;
}