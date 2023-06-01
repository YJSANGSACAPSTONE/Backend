package com.planner.godsaeng.security.jwt.exception;

import com.planner.godsaeng.error.exception.BusinessException;
import com.planner.godsaeng.error.exception.ErrorCode;

public class JWTException extends BusinessException {
	public JWTException(ErrorCode errorCode) {
		super(errorCode);
	}

}
