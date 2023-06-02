package com.planner.godsaeng.error.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {

		// Global
		ADDRESS_NOT_EXIST(HttpStatus.BAD_REQUEST, "G01", "잘못된 요청 주소입니다.."),
		INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "G02", "잘못된 입력 값입니다."),
		INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST, "G03", "잘못된 타입입니다."),
		MISSING_INPUT_VALUE(HttpStatus.BAD_REQUEST, "G04", "입력된 타입이 잘못되었습니다."),
		ACCESS_DENIED(HttpStatus.FORBIDDEN, "G05", "접근 권한이 없습니다."),
		INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C006", "내부 서버 에러."),

		
		//Plan
		PLAN_NOT_FOUND(HttpStatus.NOT_FOUND, "P001", "존재하지 않는 일정입니다."),
		PLAN_TIMELINE_INSERT_ERROR(HttpStatus.BAD_REQUEST, "P002", "타임라인 값이 잘못된 일정입니다."),

		//Challenge
		Challenge_NOT_FOUND(HttpStatus.NOT_FOUND, "C001", "삭제된 챌린지 입니다."),
		Challenge_ALREADY_FINISHED(HttpStatus.BAD_REQUEST, "C002", "이미 종료된 챌린지 입니다."),
		Challenge_ALREADY_PARTICIPATED(HttpStatus.BAD_REQUEST, "C003", "이미 참가한 챌린지입니다."),
		Challenge_LOW_DEPOSIT(HttpStatus.BAD_REQUEST, "C004", "챌린지 참가를 위한 잔고가 부족합니다."),
		Challenge_ALREADY_VERIFIED(HttpStatus.BAD_REQUEST, "C005", "이미 인증한 챌린지입니다.");


		private final HttpStatus status;
		private final String code;
		private final String message;

		ErrorCode(HttpStatus status, String code, String message) {
			this.status = status;
			this.code = code;
			this.message = message;
		}
}
