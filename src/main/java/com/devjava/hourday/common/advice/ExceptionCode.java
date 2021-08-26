package com.devjava.hourday.common.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ExceptionCode {

    // Common
    ENTITY_NOT_FOUND(HttpStatus.BAD_REQUEST, "Entity Not Found"),
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST, "Invalid Type Value"),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "Invalid Input Value"),
    METHOD_NOT_ALLOWED(HttpStatus.BAD_REQUEST, "Invalid Method"),
    JSON_WRITE_ERROR(HttpStatus.BAD_REQUEST, "Json Write Error"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"),
    SERVLET_ERROR(HttpStatus.BAD_REQUEST, "Servlet Error"),

    // JWT
    JWT_EXCEPTION(HttpStatus.BAD_REQUEST, "JWT 에러입니다."),
    NOT_VALID_USER(HttpStatus.BAD_REQUEST, "인증 실패입니다."),

    // User
    EMAIL_DUPLICATION(HttpStatus.BAD_REQUEST, "이메일 중복입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "회원이 존재하지 않습니다."),
    NICKNAME_EMAIL_DUPLICATION(HttpStatus.BAD_REQUEST, "닉네임 증복입니다."),
    PASSWORD_MISMATCH_ERROR(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    USER_NOT_AUTHENTICATION(HttpStatus.BAD_REQUEST, "권한이 존재하지 않습니다."),
    LOGIN_LOCK(HttpStatus.BAD_REQUEST, "로그인이 제한되었습니다."),

    // Schedule
    SCHEDULE_NOT_FOUND(HttpStatus.NOT_FOUND, "일정이 존재하지 않습니다."),

    // Comment
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "댓글이 존재하지 않습니다."),

    // Follow
    FOLLOW_DUPLICATION(HttpStatus.BAD_REQUEST, "이미 팔로우 한 상태입니다.");


    private final HttpStatus status;
    private final String message;

}