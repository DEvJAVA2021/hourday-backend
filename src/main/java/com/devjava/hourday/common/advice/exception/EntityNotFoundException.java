package com.devjava.hourday.common.advice.exception;

import com.devjava.hourday.common.advice.ExceptionCode;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }

}
