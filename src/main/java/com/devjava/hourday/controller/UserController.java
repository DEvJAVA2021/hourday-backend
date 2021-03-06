package com.devjava.hourday.controller;

import com.devjava.hourday.common.dto.ResponseDto;
import com.devjava.hourday.dto.user.SignInRequestDto;
import com.devjava.hourday.dto.user.SignUpRequestDto;
import com.devjava.hourday.dto.user.SearchNicknameDto;
import com.devjava.hourday.mapper.UserMapper;
import com.devjava.hourday.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> signUp(@RequestBody @Valid SignUpRequestDto requestDto) {
        return ResponseEntity.ok(ResponseDto.of(HttpStatus.CREATED, "회원가입 성공입니다.", userService.signUp(userMapper.toEntity(requestDto))));
    }

    @PostMapping("/signin")
    public ResponseEntity<ResponseDto> signIn(@RequestBody @Valid SignInRequestDto requestDto) {
        return ResponseEntity.ok(ResponseDto.of(HttpStatus.OK, "로그인 성공입니다.", userService.signIn(userMapper.toEntity(requestDto))));
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseDto> searchNickname(@RequestBody @Valid SearchNicknameDto requestDto) {
        return ResponseEntity.ok(ResponseDto.of(HttpStatus.OK, " 조회 성공입니다.", userMapper.toDto(userService.searchNickname(requestDto.getNickname()))));
    }

}
