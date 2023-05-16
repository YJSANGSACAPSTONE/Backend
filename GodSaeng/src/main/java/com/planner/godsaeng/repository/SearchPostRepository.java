package com.planner.godsaeng.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.planner.godsaeng.entity.Post;

public interface SearchPostRepository {
   Post search1();
   
   Page<Object[]> searchPage(String type, String keyword, Pageable pageable);
}