package com.devjava.hourday.dto.category;

import com.devjava.hourday.entity.Category;
import com.devjava.hourday.entity.User;
import lombok.AllArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Setter
public class CategoryRequestDto {

    @NotBlank
    private String color;

    @NotBlank
    private String categoryName;

    @NotBlank @Max(24)
    private int challengeTime;

    public Category toEntity(User user) {
        return new Category(null, user, color, categoryName, challengeTime);
    }

}
