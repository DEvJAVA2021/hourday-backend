package com.devjava.hourday.common.advice.exception.user;

import com.devjava.hourday.common.advice.ExceptionCode;
import com.devjava.hourday.common.advice.exception.InvalidValueException;
import lombok.Getter;

@Getter
public class EmailDuplicatedException extends InvalidValueException {

    public EmailDuplicatedException() {
        super(ExceptionCode.EMAIL_DUPLICATION);
    }

}