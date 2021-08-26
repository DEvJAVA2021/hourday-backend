package com.devjava.hourday.service;

import com.devjava.hourday.entity.Comment;
import com.devjava.hourday.entity.Schedule;
import com.devjava.hourday.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    
    private final CommentRepository commentRepository;
    
    public List<Comment> getCommentList(Schedule schedule) {
        // TODO 비공개, 공개 여부 확인
        return commentRepository.findAllBySchedule(schedule);
    }

}
