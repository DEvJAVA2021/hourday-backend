package com.devjava.hourday.dto;

import com.devjava.hourday.dto.user.UserDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ScheduleResponseDto {

    private String memo;

    private UserDto writer;

    private List<DetailScheduleDto> detailScheduleList = new ArrayList<>();

}
