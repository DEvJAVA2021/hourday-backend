package com.devjava.hourday.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto {

    private String name;

    private String email;

    private String nickname;

    private String password;

    private Boolean isPublic;

}
