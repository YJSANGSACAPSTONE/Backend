package com.planner.godsaeng.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@MappedSuperclass	// 테이블로 생성하지 않음
@EntityListeners(value = {AuditingEntityListener.class}) // JPA 내부에서 엔티티 객체 생성/변경 감지 역할
@Getter
abstract class BaseEntity {
	
	@CreatedDate
	@Column(name = "regdate", updatable = false)	// 엔티티 객체 DB 반영시 값 변경이 이뤄지지 않음
	private LocalDateTime regDateTime;
	
	@LastModifiedDate
	@Column(name="moddate")
	private LocalDateTime moDateTime;
}
