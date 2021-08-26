package com.devjava.hourday.dto;

import com.devjava.hourday.entity.DetailSchedule;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ScheduleResponseDto {

    private String memo;

    private List<DetailScheduleDto> detailScheduleList = new ArrayList<>();

}
