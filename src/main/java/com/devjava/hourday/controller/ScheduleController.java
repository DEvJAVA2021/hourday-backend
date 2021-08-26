package com.devjava.hourday.controller;

import com.devjava.hourday.common.dto.ResponseDto;
import com.devjava.hourday.common.jwt.auth.CurrentUser;
import com.devjava.hourday.entity.User;
import com.devjava.hourday.mapper.ScheduleMapper;
import com.devjava.hourday.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    private final ScheduleMapper scheduleMapper;

    @GetMapping("/{date}")
    public ResponseEntity<ResponseDto> getDetailScheduleList(@PathVariable String date, @CurrentUser User user) {
        return ResponseEntity.ok(ResponseDto.of(HttpStatus.OK, "스케줄 상세 조회 성공입니다.", scheduleMapper.toDto(scheduleService.getScheduleList(user, LocalDate.parse(date)))));
    }

}
