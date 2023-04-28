package com.planner.godsaeng.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {
	private int page;
	private int size;
	
	private String type; 	// 검색타입
	private String keyword; 	// 검색 키워드
	
	public PageRequestDTO() {
		this.page = 1;
		this.size = 10;
	}
	
	public Pageable getPageable(Sort sor) {
		return PageRequest.of(page - 1, size, sor);
	}

}
