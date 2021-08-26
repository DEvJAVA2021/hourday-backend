package com.devjava.hourday.common.advice.exception.user;

import com.devjava.hourday.common.advice.ExceptionCode;
import com.devjava.hourday.common.advice.exception.InvalidValueException;

public class LoginLockException extends InvalidValueException {

    public LoginLockException() {
        super(ExceptionCode.LOGIN_LOCK);
    }

}
