package com.devjava.hourday.controller;

import com.devjava.hourday.common.ResponseDto;
import com.devjava.hourday.controller.dto.user.SignInRequestDto;
import com.devjava.hourday.controller.dto.user.SignUpRequestDto;
import com.devjava.hourday.mapper.UserMapper;
import com.devjava.hourday.repository.UserRepository;
import com.devjava.hourday.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> signUp(@RequestBody SignUpRequestDto requestDto) {
        return ResponseEntity.ok(ResponseDto.of(HttpStatus.CREATED, "회원가입 성공입니다.", userService.signUp(userMapper.toEntity(requestDto))));
    }

    @PostMapping("/signin")
    public ResponseEntity<ResponseDto> signIn(@RequestBody SignInRequestDto requestDto) {
        return ResponseEntity.ok(ResponseDto.of(HttpStatus.OK, "로그인 성공입니다.", userService.signIn(userMapper.toEntity(requestDto))));
    }

}
