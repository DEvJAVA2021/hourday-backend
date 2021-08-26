package com.devjava.hourday.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SignUpRequestDto {

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    private String nickname;

    @NotBlank
    private String password;

    @NotNull
    private Boolean isPublic;

}
