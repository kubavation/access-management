package com.durys.jakub.accessmanagement.security.util;

import com.durys.jakub.accessmanagement.user.model.util.AmUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import liquibase.pro.packaged.D;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtSecurityUtils {

    private static final String SECRET_KEY = "am-secret-key";


    public String generateToken(String subject) {
        return createToken(subject);
    }


    private String createToken(String subject) {
        return Jwts.builder()
                .addClaims(new HashMap<>())
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(JwtDateUtils.getExpirationDate(new Date()))
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

    public boolean isTokenExpired(String token) {
        Date expirationDate = extractClaim(token, Claims::getExpiration);
        return expirationDate.after(new Date());
    }

    public boolean validateToken(String token, AmUserDetails userDetails) {
        return StringUtils.isEmpty(token)
                && userDetails.getUsername().equals(getUsername(token))
                && !isTokenExpired(token);
    }
}
