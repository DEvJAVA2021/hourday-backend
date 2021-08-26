package com.devjava.hourday.mapper;

import com.devjava.hourday.dto.user.SignUpRequestDto;
import com.devjava.hourday.dto.CategoryResponseDto;
import com.devjava.hourday.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponseDto toDto(Category category);

}
