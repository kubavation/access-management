package com.durys.jakub.accessmanagement.security.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import liquibase.pro.packaged.D;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class JwtSecurityUtils {

    private static final String SECRET_KEY = "am-secret-key";


    private String createToken(String subject, Map<String, Object> claims) {
        return Jwts.builder()
                .addClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date()) //todo
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }
}
