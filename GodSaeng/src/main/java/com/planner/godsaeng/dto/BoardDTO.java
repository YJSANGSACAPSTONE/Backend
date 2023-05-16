package com.planner.godsaeng.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class BoardDTO {
   private int b_id;       // 게시판 id
   private String u_id;   // 관리자 id
   private String b_name;   // 계시판 이름

}