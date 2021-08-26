package com.devjava.hourday.dto;

import com.devjava.hourday.entity.Category;
import com.devjava.hourday.entity.User;

public class CategoryRequestDto {

    private User user;
    private String color;
    private String categoryName;
    private int challengeTime;

    public CategoryRequestDto(User user, String color, String categoryName, int challengeTime) {
        this.user = user;
        this.color = color;
        this.categoryName = categoryName;
        this.challengeTime = challengeTime;
    }

    public Category toEntity() {
        return new Category(null, user, color, categoryName, challengeTime );
    }

}
