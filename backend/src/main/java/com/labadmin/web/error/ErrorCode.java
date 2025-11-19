package com.labadmin.web.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    AUTH_INVALID_CREDENTIAL(HttpStatus.UNAUTHORIZED, "아이디 또는 비밀번호가 올바르지 않음"),
    AUTH_FORBIDDEN(HttpStatus.FORBIDDEN, "권한이 없습니다"),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다"),

    SERVER_NOT_FOUND(HttpStatus.NOT_FOUND, "서버를 찾을 수 없습니다"),

    RESERVATION_NOT_FOUND(HttpStatus.NOT_FOUND, "예약을 찾을 수 없습니다"),
    RESERVATION_TIME_INVALID(HttpStatus.BAD_REQUEST, "예약 시간 범위가 올바르지 않습니다"),
    RESERVATION_OVERLAP(HttpStatus.CONFLICT, "이미 겹치는 예약이 존재합니다"),
    RESERVATION_FORBIDDEN(HttpStatus.FORBIDDEN, "해당 예약에 대한 권한이 없습니다");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
