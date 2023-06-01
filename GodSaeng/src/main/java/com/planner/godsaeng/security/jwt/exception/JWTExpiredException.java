package com.planner.godsaeng.security.jwt.exception;

import com.planner.godsaeng.error.exception.ErrorCode;

public class JWTExpiredException extends JWTException {

	public JWTExpiredException() {
		super(ErrorCode.TOKEN_EXPIRED);
	}
}
