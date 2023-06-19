package com.planner.godsaeng.hierarchy.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.planner.godsaeng.hierarchy.entity.RoleHierarchy;
import com.planner.godsaeng.hierarchy.repository.RoleHierarchyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleHierarchyServiceImpl implements RoleHierarchyService{

private final RoleHierarchyRepository roleHierarchyRepository;
	
	@Transactional
	@Override
	public String findAllHierarchy() {
		List<RoleHierarchy> roleHierarchy = roleHierarchyRepository.findAll();
		StringBuilder concatRoles = new StringBuilder();
		
		for (RoleHierarchy role : roleHierarchy) {
			if (role.getParentName() != null) {
				concatRoles.append(role.getParentName().getChildName());
				concatRoles.append(" > ");
				concatRoles.append(role.getChildName());
				concatRoles.append("\n");
			}
		}
		return concatRoles.toString();
	}
}