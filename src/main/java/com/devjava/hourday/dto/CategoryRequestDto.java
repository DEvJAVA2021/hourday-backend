package com.devjava.hourday.dto;

import com.devjava.hourday.entity.Category;
import com.devjava.hourday.entity.User;
import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
public class CategoryRequestDto {
    private String color;
    private String categoryName;
    private int challengeTime;

    public Category toEntity(User user) {
        return new Category(null, user, color, categoryName, challengeTime);
    }

}
