package com.devjava.hourday.repository;

import com.devjava.hourday.entity.Category;
import com.devjava.hourday.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByUser(User user);

}
