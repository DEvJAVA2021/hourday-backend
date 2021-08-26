package com.devjava.hourday.controller;

import com.devjava.hourday.common.dto.ResponseDto;
import com.devjava.hourday.common.jwt.auth.CurrentUser;
import com.devjava.hourday.dto.category.CategoryRequestDto;
import com.devjava.hourday.entity.User;
import com.devjava.hourday.mapper.CategoryMapper;
import com.devjava.hourday.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<ResponseDto> responseCategory(@CurrentUser User user) {
        return ResponseEntity.ok(ResponseDto.of(HttpStatus.CREATED, "카테고리 조회 성공입니다.", categoryService.getCategoryList(user).stream().map(categoryMapper::toDto).collect(toList())));
    }

    @PostMapping
    public ResponseEntity<ResponseDto> requestCategory(@RequestBody @Valid CategoryRequestDto requestDto, @CurrentUser User user) {
        // dto를 변환! Entity!
        categoryService.saveCategory(requestDto.toEntity(user));
        return ResponseEntity.ok(ResponseDto.of(HttpStatus.CREATED, "카테고리 생성 성공입니다."));
    }

}
