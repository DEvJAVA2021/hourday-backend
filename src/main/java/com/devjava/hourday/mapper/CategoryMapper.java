package com.devjava.hourday.mapper;

import com.devjava.hourday.dto.category.CategoryResponseDto;
import com.devjava.hourday.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponseDto toDto(Category category);

}
