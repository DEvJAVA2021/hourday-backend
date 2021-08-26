package com.devjava.hourday.dto.schedule;

import com.devjava.hourday.entity.DetailSchedule;
import com.devjava.hourday.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalTime;

@Getter @Setter
public class DetailScheduleDto {

    @NotBlank
    private String content;

    private final LocalTime startTime = LocalTime.now();

    @NotBlank
    private Long categoryId;

    public DetailSchedule toEntity(Schedule schedule) {
        return DetailSchedule.builder().content(content).startTime(startTime).schedule(schedule).build();
    }

}
