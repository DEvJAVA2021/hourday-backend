package com.devjava.hourday.common.advice.exception.user;

import com.devjava.hourday.common.advice.ExceptionCode;
import com.devjava.hourday.common.advice.exception.InvalidValueException;

public class UserAuthenticationException extends InvalidValueException {

    public UserAuthenticationException() {
        super(ExceptionCode.USER_NOT_AUTHENTICATION);
    }

}