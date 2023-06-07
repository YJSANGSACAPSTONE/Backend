package com.planner.godsaeng.hierarchy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planner.godsaeng.hierarchy.entity.RoleHierarchy;

public interface RoleHierarchyRepository extends JpaRepository<RoleHierarchy, Long> {
	
	RoleHierarchy findByChildName(String roleName);

}
