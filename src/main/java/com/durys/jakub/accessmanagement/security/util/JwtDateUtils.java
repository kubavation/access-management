package com.durys.jakub.accessmanagement.security.util;

import org.springframework.beans.factory.annotation.Value;

import java.time.*;
import java.util.Date;

public class JwtDateUtils {

    @Value("${jwt.hoursToExpire:1}")
    private static Integer HOURS_TO_EXPIRE;

    public static Date getExpirationDate(Date creationDate) {
       return Date.from(
               LocalDateTime.ofInstant(creationDate.toInstant(), ZoneId.systemDefault())
                    .plusHours(HOURS_TO_EXPIRE).toInstant(ZoneOffset.UTC));
    }
}
