package com.devjava.hourday.common.advice.exception.user;

import com.devjava.hourday.common.advice.ExceptionCode;
import com.devjava.hourday.common.advice.exception.InvalidValueException;

public class NicknameDuplicatedException extends InvalidValueException {

    public NicknameDuplicatedException() {
        super(ExceptionCode.NICKNAME_EMAIL_DUPLICATION);
    }

}