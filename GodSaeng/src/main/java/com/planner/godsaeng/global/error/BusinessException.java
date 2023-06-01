package com.planner.godsaeng.global.error;

import com.planner.godsaeng.global.error.exception.ErrorCode;

public class BusinessException extends RuntimeException{
	
	private final ErrorCode errorCode;
	
	public BusinessException(String message, ErrorCode errorCode) {
		super(message);
		this.errorCode =errorCode;
	}
	
	public BusinessException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}

}
