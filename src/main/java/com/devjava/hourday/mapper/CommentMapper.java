package com.devjava.hourday.mapper;

import com.devjava.hourday.dto.comment.CommentRequestDto;
import com.devjava.hourday.dto.comment.CommentResponseDto;
import com.devjava.hourday.entity.Comment;
import com.devjava.hourday.entity.Schedule;
import com.devjava.hourday.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentResponseDto toDto(Comment comment);

    @Mapping(target = "schedule", source = "schedule")
    @Mapping(target = "id", ignore = true)
    Comment toEntity(CommentRequestDto requestDto, User writer, Schedule schedule);

}
