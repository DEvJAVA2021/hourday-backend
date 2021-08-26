package com.devjava.hourday.service;

import com.devjava.hourday.entity.User;
import com.devjava.hourday.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public Long signUp(User user) {
        user.encodePassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user.getId();
    }

    public Long signIn(User user) {
        User findUser = userRepository.findByEmail(user.getEmail()).orElseThrow(IllegalArgumentException::new);

        if (!passwordEncoder.matches(user.getPassword(), findUser.getPassword())) {
            throw new IllegalArgumentException();
        }

        return findUser.getId();
    }

}
