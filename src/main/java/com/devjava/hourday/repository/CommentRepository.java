package com.devjava.hourday.repository;

import com.devjava.hourday.entity.Comment;
import com.devjava.hourday.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllBySchedule(Schedule schedule);

}
