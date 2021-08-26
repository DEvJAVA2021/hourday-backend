package com.devjava.hourday.controller;

import com.devjava.hourday.common.dto.ResponseDto;
import com.devjava.hourday.common.jwt.auth.CurrentUser;
import com.devjava.hourday.entity.User;
import com.devjava.hourday.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class FollowController {

    private final FollowService followService;

    /*
        투두메이트 방식
     */
    @PostMapping("/follow/{followerId}")
    public ResponseEntity<ResponseDto> follow(@PathVariable Long followerId, @CurrentUser User user) {
        followService.follow(user, followerId);
        return ResponseEntity.ok(ResponseDto.of(HttpStatus.OK, "팔로우 성공입니다."));
    }

}
