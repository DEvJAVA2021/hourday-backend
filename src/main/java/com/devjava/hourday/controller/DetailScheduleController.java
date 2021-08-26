package com.devjava.hourday.controller;

import com.devjava.hourday.common.dto.ResponseDto;
import com.devjava.hourday.common.jwt.auth.CurrentUser;
import com.devjava.hourday.dto.schedule.DetailScheduleDto;
import com.devjava.hourday.dto.schedule.ScheduleUpdateRequestDto;
import com.devjava.hourday.entity.DetailSchedule;
import com.devjava.hourday.entity.Schedule;
import com.devjava.hourday.entity.User;
import com.devjava.hourday.service.DetailSchedulerService;
import com.devjava.hourday.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/schedules")
public class DetailScheduleController {

    private final DetailSchedulerService detailScheduleService;
    private final ScheduleService scheduleService;

    @PostMapping(value = "/{scheduleId}")
    public ResponseEntity<ResponseDto> saveDetailSchedule(@RequestBody @Valid DetailScheduleDto requestDto, @PathVariable Long scheduleId, @CurrentUser User user) {
        Schedule schedule = scheduleService.checkValid(scheduleId, user); // 권한 확인
        DetailSchedule detailSchedule = requestDto.toEntity(schedule);
        detailScheduleService.saveDetailSchedule(detailSchedule, requestDto.getCategoryId());
        return ResponseEntity.ok(ResponseDto.of(HttpStatus.CREATED, "스케줄 세부정보 생성 성공입니다."));
    }

    @PatchMapping(value = "/detail/{detailScheduleId}")
    public ResponseEntity<ResponseDto> addEndTime(@PathVariable Long detailScheduleId, @CurrentUser User user) {
        DetailSchedule detailSchedule = detailScheduleService.checkValid(detailScheduleId, user); // 권한 확인
        detailScheduleService.updateEndTime(detailSchedule);
        return ResponseEntity.ok(ResponseDto.of(HttpStatus.OK, "끝나는 시간 설정 성공입니다."));
    }

    @DeleteMapping(value = "/detail/{detailScheduleId}")
    public ResponseEntity<ResponseDto> deleteDetailSchedule(@PathVariable Long detailScheduleId, @CurrentUser User user) {
        DetailSchedule detailSchedule = detailScheduleService.checkValid(detailScheduleId, user);
        detailScheduleService.deleteDetailSchedule(detailSchedule);
        return ResponseEntity.ok(ResponseDto.of(HttpStatus.OK, "스케줄 삭제 성공입니다."));
    }

    @PutMapping(value = "/detail/{detailScheduleId}")
    public ResponseEntity<ResponseDto> updateDetailSchedule(@RequestBody @Valid ScheduleUpdateRequestDto requestDto, @PathVariable Long detailScheduleId, @CurrentUser User user) {
        DetailSchedule detailSchedule = detailScheduleService.checkValid(detailScheduleId, user); // 권한 확인
        detailScheduleService.updateDetailSchedule(detailSchedule, requestDto);
        return ResponseEntity.ok(ResponseDto.of(HttpStatus.OK, "스케줄 수정 성공입니다."));
    }

}
