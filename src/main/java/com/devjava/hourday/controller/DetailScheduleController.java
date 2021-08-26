package com.devjava.hourday.controller;

import com.devjava.hourday.common.dto.ResponseDto;
import com.devjava.hourday.common.jwt.auth.CurrentUser;
import com.devjava.hourday.dto.DetailScheduleDto;
import com.devjava.hourday.entity.DetailSchedule;
import com.devjava.hourday.entity.Schedule;
import com.devjava.hourday.entity.User;
import com.devjava.hourday.service.DetailSchedulerService;
import com.devjava.hourday.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/schedules/{scheduleId}")
public class DetailScheduleController {

    private final DetailSchedulerService detailScheduleService;
    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<ResponseDto> saveDetailSchedule(@RequestBody DetailScheduleDto requestDto, @PathVariable Long scheduleId, @CurrentUser User user) {
        // 권한 체크
        Schedule schedule = scheduleService.checkValid(scheduleId, user);
        DetailSchedule detailSchedule = requestDto.toEntity(schedule);
        detailScheduleService.saveDetailSchedule(detailSchedule, requestDto.getCategoryId());
        return ResponseEntity.ok(ResponseDto.of(HttpStatus.CREATED, "스케줄 세부정보 생성 성공입니다."));
    }

}
