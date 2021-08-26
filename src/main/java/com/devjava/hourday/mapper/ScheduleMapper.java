package com.devjava.hourday.mapper;

import com.devjava.hourday.dto.DetailScheduleDto;
import com.devjava.hourday.dto.ScheduleResponseDto;
import com.devjava.hourday.entity.DetailSchedule;
import com.devjava.hourday.entity.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    ScheduleResponseDto toDto(Schedule schedule);

    DetailScheduleDto toDto(DetailSchedule detailSchedule);
}
