package com.devjava.hourday.common.advice.exception.user;

import com.devjava.hourday.common.advice.ExceptionCode;
import com.devjava.hourday.common.advice.exception.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException() {
        super(ExceptionCode.USER_NOT_FOUND);
    }

}
