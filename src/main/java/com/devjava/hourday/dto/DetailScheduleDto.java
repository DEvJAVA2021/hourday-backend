package com.devjava.hourday.dto;

import com.devjava.hourday.entity.Category;
import com.devjava.hourday.entity.DetailSchedule;
import com.devjava.hourday.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter @Setter
public class DetailScheduleDto {

    private String content;

    private final LocalTime startTime = LocalTime.now();

    private Long categoryId;

    public DetailSchedule toEntity(Schedule schedule) {
        return DetailSchedule.builder().content(content).startTime(startTime).schedule(schedule).build();
    }

}
