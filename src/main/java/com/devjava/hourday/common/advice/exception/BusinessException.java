package com.devjava.hourday.common.advice.exception;

import com.devjava.hourday.common.advice.ExceptionCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final ExceptionCode exceptionCode;

    public BusinessException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }
}