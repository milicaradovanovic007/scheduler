package com.milicaradovanovic.sap.security;

import com.milicaradovanovic.sap.entity.ScheduleUserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
public class JwtProvider {


    private String secretKey;
    private long accessValidityInMilliseconds;

    @Autowired
    public JwtProvider(@Value("${security.jwt.token.secret-key}") String secretKey,
                       @Value("${security.jwt.token.expiration}") long accessValidityInMilliseconds) {
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        this.accessValidityInMilliseconds = accessValidityInMilliseconds;
    }


    public String createAccessToken(ScheduleUserEntity userEntity) {
        Claims claims = Jwts.claims().setSubject(userEntity.getEmail()).setId(String.valueOf(userEntity.getId()));

        Date now = new Date();
        Date expiresAt = new Date(now.getTime() + this.accessValidityInMilliseconds);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiresAt)
                .signWith(SignatureAlgorithm.HS256, this.secretKey)
                .compact();
    }

    public boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey(this.secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getEmail(String token) {
        return Jwts.parser().setSigningKey(this.secretKey)
                .parseClaimsJws(token).getBody().getSubject();
    }

    public long getUserId(String token) {
        return Long.parseLong(Jwts.parser().setSigningKey(this.secretKey)
                .parseClaimsJws(token).getBody().getId());
    }
}