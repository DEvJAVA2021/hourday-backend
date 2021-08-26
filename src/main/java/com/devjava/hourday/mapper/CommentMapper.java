package com.devjava.hourday.mapper;

import com.devjava.hourday.dto.CommentResponseDto;
import com.devjava.hourday.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentResponseDto toDto(Comment comment);
}
