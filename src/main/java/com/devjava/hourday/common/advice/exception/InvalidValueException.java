package com.devjava.hourday.common.advice.exception;

import com.devjava.hourday.common.advice.ExceptionCode;

public class InvalidValueException extends BusinessException {

    public InvalidValueException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

}