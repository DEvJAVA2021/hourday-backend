package com.devjava.hourday.controller.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequestDto {

    private String email;

    private String password;

}
