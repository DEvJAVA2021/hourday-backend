package com.devjava.hourday.repository;

import com.devjava.hourday.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
