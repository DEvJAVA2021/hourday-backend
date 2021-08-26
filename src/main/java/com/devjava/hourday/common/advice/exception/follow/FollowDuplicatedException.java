package com.devjava.hourday.common.advice.exception.follow;

import com.devjava.hourday.common.advice.ExceptionCode;
import com.devjava.hourday.common.advice.exception.InvalidValueException;
import lombok.Getter;

@Getter
public class FollowDuplicatedException extends InvalidValueException {

    public FollowDuplicatedException() {
        super(ExceptionCode.FOLLOW_DUPLICATION);
    }

}