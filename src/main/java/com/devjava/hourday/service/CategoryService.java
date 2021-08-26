package com.devjava.hourday.service;

import com.devjava.hourday.entity.Category;
import com.devjava.hourday.entity.User;
import com.devjava.hourday.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getCategoryList(User user) {
        return categoryRepository.findAllByUser(user);
    }

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

}
