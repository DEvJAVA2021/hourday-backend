package com.devjava.hourday.service;

import com.devjava.hourday.common.advice.exception.user.*;
import com.devjava.hourday.common.jwt.dto.TokenDto;
import com.devjava.hourday.common.jwt.service.JwtService;
import com.devjava.hourday.entity.User;
import com.devjava.hourday.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public TokenDto signUp(User user) {
        checkValid(user); // 중복 검사
        user.encodePassword(passwordEncoder.encode(user.getPassword())); // 비밀번호 암호화
        userRepository.save(user);
        return jwtService.issue(user);
    }

    public TokenDto signIn(User user) {
        User findUser = userRepository.findByEmail(user.getEmail()).orElseThrow(UserNotFoundException::new);
        if (findUser.getIsLock()) {
            // 5분 * 제한 횟수 가 지나면 제한이 풀린다.
            if ((LocalDateTime.now()).isAfter(findUser.getLatestLockDate().plusMinutes(5L * findUser.getLockCount()))) {
                findUser.setIsLock(false);
            }
            else {
                throw new LoginLockException();
            }
        }
        // 비밀번호 제한 횟수 5번
        if (!passwordEncoder.matches(user.getPassword(), findUser.getPassword())) { // 비밀번호 일치 확인
            int failCount = findUser.getLoginFailCount();
            if (failCount == 4) {
                findUser.setIsLock(true); // 제한 설정
                findUser.setLockCount(findUser.getLockCount() + 1); // 제한 횟수 증가
                findUser.setLoginFailCount(0); // 로그인 실패 횟수 초기화
                findUser.setLatestLockDate(LocalDateTime.now());
                userRepository.save(findUser);
                throw new LoginLockException();
            }
            findUser.setLoginFailCount(failCount + 1);
            userRepository.save(findUser);
            throw new PasswordNotMatchException();
        }
        findUser.setLockCount(0);
        findUser.setLoginFailCount(0);
        userRepository.save(findUser);

        return jwtService.issue(findUser);
    }

    private void checkValid(User user) {
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
