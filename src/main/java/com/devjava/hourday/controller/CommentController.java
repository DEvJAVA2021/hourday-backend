package com.devjava.hourday.controller;

import com.devjava.hourday.common.dto.ResponseDto;
import com.devjava.hourday.common.jwt.auth.CurrentUser;
import com.devjava.hourday.entity.User;
import com.devjava.hourday.mapper.CommentMapper;
import com.devjava.hourday.service.CommentService;
import com.devjava.hourday.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class CommentController {

    private final CommentService commentService;
    private final ScheduleService scheduleService;

    private final CommentMapper commentMapper;

    @GetMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<ResponseDto> getCommentList(@PathVariable Long scheduleId, @CurrentUser User user) {
        return ResponseEntity.ok(ResponseDto.of(HttpStatus.OK, "댓글 리스트 조회 성공입니다.", commentService.getCommentList(scheduleService.getScheduleById(scheduleId), user).stream().map(commentMapper::toDto).collect(toList())));
    }

}
