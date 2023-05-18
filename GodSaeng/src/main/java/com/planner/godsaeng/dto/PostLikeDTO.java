package com.planner.godsaeng.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostLikeDTO {
    private Long like_id; // 좋아요 ID
    private Long po_id; // 게시물 ID
    private String u_Id; // 사용자 ID
}
