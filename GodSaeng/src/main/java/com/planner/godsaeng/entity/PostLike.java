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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "godsaeng_post_like")
@ToString(exclude = {"user", "post"})
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
public class PostLike {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long likeid;

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	private Post post;

	@Column(columnDefinition = "boolean default false")
	private boolean liked;

	public void setLiked(boolean liked) {
		this.liked = liked;
	}
}
