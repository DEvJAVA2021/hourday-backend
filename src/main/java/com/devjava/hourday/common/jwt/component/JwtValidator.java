package com.devjava.hourday.common.jwt.component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtValidator {

    @Value("${jwt.key}")
    private String secretKey;

    @PostConstruct
    private void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public boolean validateToken(String jwtToken){
        return validateTokenBefore(jwtToken, new Date());
    }

    public boolean validateTokenBefore(String jwtToken, Date date){
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(date);
        } catch (Exception e) {
            return false;
        }
    }
}
