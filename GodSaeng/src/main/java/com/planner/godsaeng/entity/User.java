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
   private String uid;
   
   @Column(length=20, nullable=false)
   private String unickname;
   
   @Column(length=20, nullable=false)
   private String uzepetoid;
   
   @Column(length=20, nullable=false)
   private String uimg;
   
   @Column(length=20, columnDefinition = "int default 0")
   private int udeposit;
   
   @Column(length=20, nullable=false)
   private String ugrade;
   
   @Column(length=20, columnDefinition = "int default 0")
   private int ulevel;
   
   @Column(length=20, nullable=false)
   private String uemail;
   
   @Column(length=20, nullable=false)
   private String usuccessedchallenge;
   
}