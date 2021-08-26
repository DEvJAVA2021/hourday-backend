package com.devjava.hourday.common.advice;

import com.devjava.hourday.common.dto.ResponseDto;
import com.devjava.hourday.common.advice.exception.BusinessException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 접근 기록, 인증 정보 실패 기록
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ResponseDto> businessException(final BusinessException e) {
        log.error("Business Exception : " + e.getMessage()); // 로깅 처리
        final ExceptionCode exceptionCode = e.getExceptionCode();
        return ResponseEntity.badRequest().body(ResponseDto.of(exceptionCode.getStatus(), exceptionCode.getMessage()));
    }

    @ExceptionHandler(InvalidFormatException.class)
    protected ResponseEntity<ResponseDto> invalidFormatException(final InvalidFormatException e) {
        log.error("DateTimeParseException : " + e.getMessage());
        final ExceptionCode exceptionCode = ExceptionCode.INVALID_TYPE_VALUE;
        return ResponseEntity.badRequest().body(ResponseDto.of(exceptionCode.getStatus(), exceptionCode.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ResponseDto> httpMessageNotReadableException() {
        log.error("HttpMessageNotReadableException");
        final ExceptionCode exceptionCode = ExceptionCode.INVALID_TYPE_VALUE;
        return ResponseEntity.badRequest().body(ResponseDto.of(exceptionCode.getStatus(), exceptionCode.getMessage()));
    }

    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ResponseDto> handleHttpRequestMethodNotSupportedException() {
        log.error("HttpRequestMethodNotSupportedException");
        final ExceptionCode exceptionCode = ExceptionCode.METHOD_NOT_ALLOWED;
        return ResponseEntity.badRequest().body(ResponseDto.of(exceptionCode.getStatus(), exceptionCode.getMessage()));
    }


    /**
     * 예외 처리
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ResponseDto> handleException(final Exception e) {
        log.error("Exception : " + e.getMessage());
        final ExceptionCode exceptionCode = ExceptionCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.badRequest().body(ResponseDto.of(exceptionCode.getStatus(), exceptionCode.getMessage()));
    }

}