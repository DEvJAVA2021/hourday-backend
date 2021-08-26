package com.devjava.hourday.dto.schedule;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalTime;

@Getter @Setter
public class ScheduleUpdateRequestDto {

    @NotBlank
    private String content;

    private LocalTime startTime;

    private LocalTime endTime;

}
