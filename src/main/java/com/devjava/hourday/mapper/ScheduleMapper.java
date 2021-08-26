package com.devjava.hourday.mapper;

import com.devjava.hourday.dto.schedule.DetailScheduleDto;
import com.devjava.hourday.dto.schedule.ScheduleResponseDto;
import com.devjava.hourday.entity.DetailSchedule;
import com.devjava.hourday.entity.Schedule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    ScheduleResponseDto toDto(Schedule schedule);

    DetailScheduleDto toDto(DetailSchedule detailSchedule);
}
