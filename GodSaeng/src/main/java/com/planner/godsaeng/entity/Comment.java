package com.planner.godsaeng.entity;

import javax.persistence.*;

import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"post", "user"})
@Table(name="godsaeng_comment")
public class Comment extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Post post;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	private String text;
	
	public void changeText(String text){
        this.text = text;
    }
}
