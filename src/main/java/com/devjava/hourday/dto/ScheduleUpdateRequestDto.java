package com.devjava.hourday.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter @Setter
public class ScheduleUpdateRequestDto {

    private String content;

    private LocalTime startTime;

    private LocalTime endTime;

}
