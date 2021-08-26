package com.devjava.hourday.common.jwt.component;

import com.devjava.hourday.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtCreator {

    @Value("${jwt.key}")
    private String secretKey;

    @PostConstruct
    private void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createAccessToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        // 24시간
        long ACCESS_TOKEN_VALID_TIME = 1000L * 60 * 60 * 24;

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALID_TIME))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

}
