package com.planner.godsaeng.security.jwt.exception;

import com.planner.godsaeng.global.error.exception.ErrorCode;

public class JWTInvalidException extends JWTException {

	public JWTInvalidException() {
		super(ErrorCode.TOKEN_INVALID);
	}
}
