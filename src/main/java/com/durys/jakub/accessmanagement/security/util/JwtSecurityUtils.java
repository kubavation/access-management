package com.durys.jakub.accessmanagement.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import liquibase.pro.packaged.D;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

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

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
}
