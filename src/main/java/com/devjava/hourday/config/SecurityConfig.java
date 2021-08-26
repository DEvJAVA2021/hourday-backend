package com.devjava.hourday.config;

import com.devjava.hourday.common.jwt.component.JwtAuthenticationFilter;
import com.devjava.hourday.common.jwt.component.JwtResolver;
import com.devjava.hourday.common.jwt.component.JwtValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtResolver jwtResolver;
    private final JwtValidator jwtValidator;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/users/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtResolver, jwtValidator), UsernamePasswordAuthenticationFilter.class);
    }
}
