package com.devjava.hourday.controller;

import com.devjava.hourday.dto.CategoryRequestDto;
import com.devjava.hourday.entity.Category;
import com.devjava.hourday.entity.User;
import com.devjava.hourday.repository.CategoryRepository;
import com.devjava.hourday.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CategoryController {

//    @Autowired
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @GetMapping("/api/categories/{userId}")
    public List<Category> responseCategory(@PathVariable Long userId) {
        User user = userRepository.findById(userId).get();
        return categoryRepository.findAllByUser(user);
    }

    @PostMapping()
    public void requestCategory(CategoryRequestDto form) {

        // 1. dto를 변환! Entity!
        Category category = form.toEntity();

        // 2. Reposi에 entity를 DB안에 저장하게 함!
        Category saved = categoryRepository.save(category);

  //      "categoryName": "피아노치기",
    //    "color": "234733",
      //  "challengeTime": "1" // 시간당
    }


}
