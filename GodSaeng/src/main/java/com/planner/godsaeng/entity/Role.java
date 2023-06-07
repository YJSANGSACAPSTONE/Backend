package com.planner.godsaeng.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
	TEMP("ROLE_TEMP"),
	USER("ROLE_USER"),
	CHALLENGEMANAGER("ROLE_CHALLENGEMANAGER"),
	LIBMANAGER("ROLE_LIBMANAGER"),
	ADMIN("ROLE_ADMIN"),
	TEMP("ROLE_TEMP");

	private final String key;
}
