package com.planner.godsaeng.security.jwt.exception;

import com.planner.godsaeng.global.error.exception.BusinessException;
import com.planner.godsaeng.global.error.exception.ErrorCode;

public class JWTException extends BusinessException {
	public JWTException(ErrorCode errorCode) {
		super(errorCode);
	}

}
