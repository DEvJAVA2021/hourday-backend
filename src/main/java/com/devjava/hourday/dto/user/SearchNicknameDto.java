package com.devjava.hourday.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SearchNicknameDto {

    @NotBlank
    private String nickname;

}
