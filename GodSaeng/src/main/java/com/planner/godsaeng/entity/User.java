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
@AllArgsConstructor
@NoArgsConstructor
public class User {
   
   @Id
   @Column(length=30, nullable=false)
   private String uid;
   
   @Column(length=20, nullable=false)
   private String unickname;
   
   @Column(length=20, nullable=true)
   private String uzepid;

   @Column(length=20, columnDefinition = "int default 0")
   private int udeposit;
   
   @Column(length=20, columnDefinition = "VARCHAR(20) DEFAULT ''")
   private String ugrade;
   
   @Column(length=20, columnDefinition = "int default 0")
   private int ulevel;
   
   @Column(length=100, nullable=false)
   private String ucontent;
   
   @Column(length=20, nullable=true)
   private String usuccessedchallenge;
   
}