package com.devjava.hourday.service;

import com.devjava.hourday.common.advice.exception.follow.FollowDuplicatedException;
import com.devjava.hourday.entity.Follow;
import com.devjava.hourday.entity.User;
import com.devjava.hourday.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FollowService {

    private final UserService userService;
    private final FollowRepository followRepository;

    public void follow(User following, Long followerId) {
        User follower = userService.getUserById(followerId);
        if (followRepository.existsByFollowingAndFollower(following, follower)) {
            throw new FollowDuplicatedException();
        }
        followRepository.save(Follow.builder().following(following).follower(follower).build());
    }

}
