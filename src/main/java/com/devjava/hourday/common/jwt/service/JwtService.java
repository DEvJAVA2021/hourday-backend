package com.devjava.hourday.common.jwt.service;

import com.devjava.hourday.common.jwt.component.JwtCreator;
import com.devjava.hourday.common.jwt.dto.TokenDto;
import com.devjava.hourday.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class JwtService {

    private final JwtCreator jwtCreator;

    @Transactional
    public TokenDto issue(User user) {
        return new TokenDto(jwtCreator.createAccessToken(user), user.getNickname());
    }
}
