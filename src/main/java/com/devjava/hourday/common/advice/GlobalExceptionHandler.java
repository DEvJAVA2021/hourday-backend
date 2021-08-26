package com.devjava.hourday.common.advice;

import com.devjava.hourday.common.ResponseDto;
import com.devjava.hourday.common.advice.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
        return ResponseEntity.badRequest().body(ResponseDto.of(exceptionCode.getStatus(), exceptionCode.getMessage(), exceptionCode.getStatus()));
    }


    /**
     * 예외 처리
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ResponseDto> handleException(final Exception e) {
        log.error("Exception : " + e.getMessage());
        final ExceptionCode exceptionCode = ExceptionCode.INTERNAL_SERVER_ERROR;
        return ResponseEntity.badRequest().body(ResponseDto.of(exceptionCode.getStatus(), exceptionCode.getMessage(), exceptionCode.getStatus()));
    }

}