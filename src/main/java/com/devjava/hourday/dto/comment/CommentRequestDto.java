package com.devjava.hourday.dto.comment;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class CommentRequestDto {

    @NotBlank
    private String content;

}
