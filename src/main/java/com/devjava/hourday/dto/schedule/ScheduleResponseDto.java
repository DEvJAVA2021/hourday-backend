package com.devjava.hourday.dto.schedule;

import com.devjava.hourday.dto.user.UserDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ScheduleResponseDto {

    private Long id;

    private LocalDate writeDate;

    private String memo;

    private UserDto writer;

    private List<DetailScheduleDto> detailScheduleList;

}
