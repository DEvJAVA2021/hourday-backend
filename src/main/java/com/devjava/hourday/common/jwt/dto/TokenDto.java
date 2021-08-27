package com.devjava.hourday.common.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TokenDto {
    private final String accessToken;
    private final String nickname;
}