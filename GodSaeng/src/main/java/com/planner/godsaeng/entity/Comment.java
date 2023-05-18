package com.planner.godsaeng.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name="godsaeng_comment")
public class Comment extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Post post;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	private String commtext;
	
	public void changeText(String commtext){
        this.commtext = commtext;
	
	}

	
}
