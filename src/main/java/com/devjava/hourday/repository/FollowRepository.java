package com.devjava.hourday.repository;

import com.devjava.hourday.entity.Follow;
import com.devjava.hourday.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    boolean existsByFollowingAndFollower(User following, User follower);

}
