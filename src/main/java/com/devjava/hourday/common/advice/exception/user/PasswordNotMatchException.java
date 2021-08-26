package com.devjava.hourday.common.advice.exception.user;

import com.devjava.hourday.common.advice.ExceptionCode;
import com.devjava.hourday.common.advice.exception.InvalidValueException;

public class PasswordNotMatchException extends InvalidValueException {

    public PasswordNotMatchException() {
        super(ExceptionCode.PASSWORD_MISMATCH_ERROR);
    }
}