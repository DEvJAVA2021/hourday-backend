package com.devjava.hourday.dto;

import com.devjava.hourday.dto.user.UserDto;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentResponseDto {

    private Long id;

    private UserDto writer;

    private String content;

}
