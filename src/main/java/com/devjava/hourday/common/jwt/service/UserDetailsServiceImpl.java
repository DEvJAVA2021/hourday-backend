package com.devjava.hourday.common.jwt.service;

import com.devjava.hourday.common.advice.exception.user.UserNotFoundException;
import com.devjava.hourday.common.jwt.auth.UserDetailsImpl;
import com.devjava.hourday.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new UserDetailsImpl(userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new));
    }

}
