package com.devjava.hourday.service;

import com.devjava.hourday.common.advice.exception.user.EmailDuplicatedException;
import com.devjava.hourday.common.advice.exception.user.NicknameDuplicatedException;
import com.devjava.hourday.common.advice.exception.user.PasswordNotMatchException;
import com.devjava.hourday.common.advice.exception.user.UserNotFoundException;
import com.devjava.hourday.common.jwt.dto.TokenDto;
import com.devjava.hourday.common.jwt.service.JwtService;
import com.devjava.hourday.entity.User;
import com.devjava.hourday.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public TokenDto signUp(User user) {
        validate(user); // 중복 검사
        user.encodePassword(passwordEncoder.encode(user.getPassword())); // 비밀번호 암호화
        userRepository.save(user);
        return jwtService.issue(user);
    }

    public TokenDto signIn(User user) {
        User findUser = userRepository.findByEmail(user.getEmail()).orElseThrow(UserNotFoundException::new);
        if (!passwordEncoder.matches(user.getPassword(), findUser.getPassword())) { // 비밀번호 일치 확인
            throw new PasswordNotMatchException();
        }
        return jwtService.issue(findUser);
    }

    private void validate(User user) {
        if (userRepository.existsByEmail(user.getEmail())) throw new EmailDuplicatedException();
        if (userRepository.existsByNickname(user.getNickname())) throw new NicknameDuplicatedException();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public User searchNickname(String nickname) {
        return userRepository.findByNickname(nickname).orElseThrow(UserNotFoundException::new);
    }

}
