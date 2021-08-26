package com.devjava.hourday.controller;

import com.devjava.hourday.common.dto.ResponseDto;
import com.devjava.hourday.common.jwt.auth.CurrentUser;
import com.devjava.hourday.dto.DetailScheduleDto;
import com.devjava.hourday.entity.DetailSchedule;
import com.devjava.hourday.entity.User;
import com.devjava.hourday.service.DetailSchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/schedules/{scheduleId}")
public class DetailScheduleController {

    private final DetailSchedulerService detailScheduleService;

//    @GetMapping
//    public ResponseEntity<ResponseDto> responseDetailSchedule(@CurrentUser User user) {
//        return ResponseEntity.ok(ResponseDto.of(HttpStatus.CREATED, "스케줄 세부정보 조회 성공입니다.", detailScheduleService.getDetailScheduleList(schedule).stream().map(detailScheduleMapper::toDto).collect(toList())));
//    }

    @PostMapping
    public ResponseEntity<ResponseDto> saveDetailSchedule(@RequestBody DetailScheduleDto requestDto, @PathVariable Long scheduleId, @CurrentUser User user) {
        DetailSchedule detailSchedule = requestDto.toEntity();
        detailScheduleService.saveDetailSchedule(detailSchedule, scheduleId, requestDto.getCategoryId());
        return ResponseEntity.ok(ResponseDto.of(HttpStatus.CREATED, "스케줄 세부정보 생성 성공입니다."));
    }

}
