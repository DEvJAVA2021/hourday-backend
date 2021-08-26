package com.devjava.hourday.service;

import com.devjava.hourday.common.advice.exception.user.UserAuthenticationException;
import com.devjava.hourday.entity.Comment;
import com.devjava.hourday.entity.Schedule;
import com.devjava.hourday.entity.User;
import com.devjava.hourday.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    
    private final CommentRepository commentRepository;
    private final FollowService followService;
    
    public List<Comment> getCommentList(Schedule schedule, User user) {
        if (schedule.getWriter().getIsPublic() || followService.checkFollow(user, schedule.getWriter())) {
            return commentRepository.findAllBySchedule(schedule);
        }
        else {
            throw new UserAuthenticationException();
        }
    }

}
