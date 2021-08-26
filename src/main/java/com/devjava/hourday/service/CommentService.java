package com.devjava.hourday.service;

import com.devjava.hourday.common.advice.exception.comment.CommentNotFoundException;
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
    
    private final FollowService followService;

    private final CommentRepository commentRepository;

    public List<Comment> getCommentList(Schedule schedule, User user) {
        if (checkValid(schedule, user)) {
            return commentRepository.findAllBySchedule(schedule);
        }
        else {
            throw new UserAuthenticationException();
        }
    }

    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
    }

    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    public void updateComment(Comment comment, String content) {
        comment.setContent(content);
        commentRepository.save(comment);
    }

    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }

    public void checkUpdateValid(Comment comment, User user) {
        if (!comment.getWriter().getId().equals(user.getId())) {
            throw new UserAuthenticationException();
        }
    }

    public void checkDeleteValid(Comment comment, User user) {
        if (!comment.getWriter().getId().equals(user.getId()) && !comment.getSchedule().getWriter().getId().equals(user.getId())) {
            throw new UserAuthenticationException();
        }
    }

    public boolean checkValid(Schedule schedule, User user) {
        return schedule.getWriter().getIsPublic() || followService.checkFollow(user, schedule.getWriter());
    }

}
