package com.durys.jakub.accessmanagement.security.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.*;
import java.util.Date;

@Component
public class JwtDateUtils {

    @Value("${jwt.hoursToExpire:1}")
    private Integer hoursToExpire;

    public Date getExpirationDate(Date creationDate) {
        return Date.from(
                LocalDateTime.ofInstant(creationDate.toInstant(), ZoneId.systemDefault())
                        .plusHours(hoursToExpire).toInstant(ZoneOffset.UTC));
    }

}
