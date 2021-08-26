package com.devjava.hourday.common.jwt.component;

import com.devjava.hourday.common.jwt.service.UserDetailsServiceImpl;
import com.devjava.hourday.entity.Schedule;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

@RequiredArgsConstructor
@Component
public class JwtResolver {

    @Value("${jwt.key}")
    private String secretKey;

    private final UserDetailsServiceImpl userDetailsService;

    @PostConstruct
    private void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    public String getAuthenticationMemberId(String token){
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody().getSubject();
    }

    public Authentication getAuthentication(String token){
        UserDetails member = userDetailsService.loadUserByUsername(getAuthenticationMemberId(token));
        return new UsernamePasswordAuthenticationToken(member, "", null);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .parseClaimsJws(token)
                .getBody();
    }

}
