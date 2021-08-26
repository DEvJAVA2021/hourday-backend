package com.devjava.hourday.controller;

import com.devjava.hourday.common.advice.exception.user.UserAuthenticationException;
import com.devjava.hourday.common.dto.ResponseDto;
import com.devjava.hourday.common.jwt.auth.CurrentUser;
import com.devjava.hourday.dto.CommentRequestDto;
import com.devjava.hourday.entity.Schedule;
import com.devjava.hourday.entity.User;
import com.devjava.hourday.mapper.CommentMapper;
import com.devjava.hourday.service.CommentService;
import com.devjava.hourday.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/schedules/{scheduleId}/comments")
public class CommentController {

    private final CommentService commentService;
    private final ScheduleService scheduleService;

    private final CommentMapper commentMapper;

    @GetMapping
    public ResponseEntity<ResponseDto> getCommentList(@PathVariable Long scheduleId, @CurrentUser User user) {
        return ResponseEntity.ok(ResponseDto.of(HttpStatus.OK, "댓글 리스트 조회 성공입니다.", commentService.getCommentList(scheduleService.getScheduleById(scheduleId), user).stream().map(commentMapper::toDto).collect(toList())));
    }

    @PostMapping
    public ResponseEntity<ResponseDto> saveComment(@RequestBody CommentRequestDto requestDto, @PathVariable Long scheduleId, @CurrentUser User user) {
        Schedule schedule = scheduleService.getScheduleById(scheduleId);
        if (!commentService.checkValid(schedule, user)) {
            throw new UserAuthenticationException();
        }
        commentService.saveComment(commentMapper.toEntity(requestDto, user, schedule));
        return ResponseEntity.ok(ResponseDto.of(HttpStatus.CREATED, "댓글 작성 성공입니다."));
    }

}
