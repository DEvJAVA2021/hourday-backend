package com.devjava.hourday.controller;

import com.devjava.hourday.common.advice.exception.user.UserAuthenticationException;
import com.devjava.hourday.common.dto.ResponseDto;
import com.devjava.hourday.common.jwt.auth.CurrentUser;
import com.devjava.hourday.dto.comment.CommentRequestDto;
import com.devjava.hourday.entity.Comment;
import com.devjava.hourday.entity.Schedule;
import com.devjava.hourday.entity.User;
import com.devjava.hourday.mapper.CommentMapper;
import com.devjava.hourday.service.CommentService;
import com.devjava.hourday.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<ResponseDto> saveComment(@RequestBody @Valid CommentRequestDto requestDto, @PathVariable Long scheduleId, @CurrentUser User user) {
        Schedule schedule = scheduleService.getScheduleById(scheduleId);
        if (!commentService.checkValid(schedule, user)) {
            throw new UserAuthenticationException();
        }
        commentService.saveComment(commentMapper.toEntity(requestDto, user, schedule));
        return ResponseEntity.ok(ResponseDto.of(HttpStatus.CREATED, "댓글 작성 성공입니다."));
    }

    @PatchMapping("/comments/{commentId}")
    public ResponseEntity<ResponseDto> updateComment(@RequestBody @Valid CommentRequestDto requestDto, @PathVariable Long commentId, @CurrentUser User user) {
        Comment comment = commentService.getCommentById(commentId);
        commentService.checkUpdateValid(comment, user); // 댓글 작성자 수정 권한 O
        commentService.updateComment(comment, requestDto.getContent());
        return ResponseEntity.ok(ResponseDto.of(HttpStatus.OK, "댓글 수정 성공입니다."));
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ResponseDto> deleteComment(@PathVariable Long commentId, @CurrentUser User user) {
        Comment comment = commentService.getCommentById(commentId);
        commentService.checkDeleteValid(comment, user); // 스케줄 작성자와 댓글 작성자 삭제 권한 O
        commentService.deleteComment(comment);
        return ResponseEntity.ok(ResponseDto.of(HttpStatus.OK, "댓글 삭제 성공입니다."));
    }

}
