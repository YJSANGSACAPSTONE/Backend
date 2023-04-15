package com.planner.godsaeng.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="godsaeng_user")
@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
   
   @Id
   @Column(length=20, nullable=false)
   private String u_id;
   
   @Column(length=20, nullable=false)
   private String u_nickname;
   
   @Column(length=20, nullable=false)
   private String u_zepetoid;
   
   @Column(length=20, nullable=false)
   private String u_img;
   
   @Column(length=20, columnDefinition = "int default 0")
   private int u_deposit;
   
   @Column(length=20, columnDefinition = "int default 5")
   private String u_grade;
   
   @Column(length=20, nullable=false)
   private String u_email;
   
   @Column(length=20, nullable=false)
   private String u_successedchallenge;
   
}