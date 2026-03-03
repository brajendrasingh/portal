package com.bksoft.questionbank.utils;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenValidator {

    @Value("${jwt.secretKey}")
    private String secretKey;

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).build().parseSignedClaims(token).getBody().getSubject();
    }
}
