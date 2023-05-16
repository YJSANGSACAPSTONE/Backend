package com.planner.godsaeng.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.querydsl.core.annotations.Generated;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="godsaeng_post_image")
@ToString(exclude = "post")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class PostImage {
   
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
   private Long iid;         // 이미지 넘버(ID)
   
   @ManyToOne(fetch = FetchType.LAZY)
   private Post post;         // 게시판 ID
 
   @Column(length = 200, nullable = false)
   private String imgName;      // 원본 이미지 이름
   
   private String uuid;      // 변경된 파일 이름
   
   private String path;
   
   
   
}