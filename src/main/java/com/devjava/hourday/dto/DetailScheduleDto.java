package com.devjava.hourday.dto;

import com.devjava.hourday.entity.Category;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter @Setter
public class DetailScheduleDto {

    private String content;

    private LocalTime startTime;

    private LocalTime endTime;

}
