package com.planner.godsaeng;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.stereotype.Component;

import com.planner.godsaeng.hierarchy.service.RoleHierarchyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class SecurityInitializer implements ApplicationRunner {
	
	private final RoleHierarchyService roleHierarchyService;
	
	private final RoleHierarchyImpl roleHierarchy;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		String allHierarchy = roleHierarchyService.findAllHierarchy();
		log.info("----------------" + allHierarchy);
		roleHierarchy.setHierarchy(allHierarchy);
	}
}
