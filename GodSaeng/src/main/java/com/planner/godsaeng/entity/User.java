package com.planner.godsaeng.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CascadeType;

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
   @Column(length=100, nullable=false)
   private String uid;
   
   @Column(length=20, nullable=false)
   private String unickname;
   
   @Column(length=100, nullable=true)
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
   
   @Column(nullable= false, columnDefinition = "int default 0")
   private int uverifiedornot;
   
//   @OneToMany(mappedBy = "user", cascade = javax.persistence.CascadeType.ALL, orphanRemoval = true)
//   private List<ChallengeParticipate> challengeParticipateList = new ArrayList<>();
//   
//   @OneToMany(mappedBy = "user", cascade = javax.persistence.CascadeType.ALL, orphanRemoval = true)
//   private List<Plan> plans;
//   
//   @OneToMany(mappedBy = "user", cascade = javax.persistence.CascadeType.ALL)
//   private List<Payment> payments;

}
   