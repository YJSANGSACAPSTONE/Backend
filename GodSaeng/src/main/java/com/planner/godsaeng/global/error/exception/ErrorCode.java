package com.planner.godsaeng.global.error.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {
	
	INVAILD_INPUT_VALUE(HttpStatus.BAD_REQUEST, "RQE001", "잘못된 입력 값입니다."),
	INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST, "RQE002", "잘못된 입력 타입입니다."),
	INVALID_FILE_TYPE(HttpStatus.BAD_REQUEST, "RQE003", "잘못된 파일 타입입니다.");
	
	
	
	private final HttpStatus status;
	private final String code;
	private final String message;
	
	ErrorCode(HttpStatus status, String code, String message){
		this.status = status;
		this.code = code;
		this.message = message;
	}

}
