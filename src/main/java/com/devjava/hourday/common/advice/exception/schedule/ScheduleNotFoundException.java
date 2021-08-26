package com.devjava.hourday.common.advice.exception.schedule;


import com.devjava.hourday.common.advice.ExceptionCode;
import com.devjava.hourday.common.advice.exception.EntityNotFoundException;

public class ScheduleNotFoundException extends EntityNotFoundException {

    public ScheduleNotFoundException() {
        super(ExceptionCode.SCHEDULE_NOT_FOUND);
    }
}
