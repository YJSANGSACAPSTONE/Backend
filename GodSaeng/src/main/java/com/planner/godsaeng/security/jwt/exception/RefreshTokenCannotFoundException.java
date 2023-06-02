package com.planner.godsaeng.security.jwt.exception;

import com.planner.godsaeng.error.exception.BusinessException;
import com.planner.godsaeng.error.exception.ErrorCode;

public class RefreshTokenCannotFoundException extends BusinessException {
	public RefreshTokenCannotFoundException() {
		super(ErrorCode.REFRESH_TOKEN_NOT_FOUND);
	}

}
