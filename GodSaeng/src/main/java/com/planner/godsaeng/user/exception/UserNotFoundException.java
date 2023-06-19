package com.planner.godsaeng.user.exception;


import com.planner.godsaeng.global.error.exception.BusinessException;
import com.planner.godsaeng.global.error.exception.ErrorCode;

public class UserNotFoundException extends BusinessException {

	public UserNotFoundException() {
		super(ErrorCode.USER_NOT_FOUND);
	}
	
}
