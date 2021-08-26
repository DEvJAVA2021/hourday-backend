package com.devjava.hourday.mapper;

import com.devjava.hourday.controller.dto.user.SignInRequestDto;
import com.devjava.hourday.controller.dto.user.SignUpRequestDto;
import com.devjava.hourday.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "scheduleList", ignore = true)
    User toEntity(SignUpRequestDto requestDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "nickname", ignore = true)
    @Mapping(target = "isPublic", ignore = true)
    @Mapping(target = "scheduleList", ignore = true)
    User toEntity(SignInRequestDto requestDto);

}
