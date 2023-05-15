package com.durys.jakub.accessmanagement.shared.keycloak.token;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KeycloakTokenUtils {

    public static String principalUsername() {
        return ((String) principalToken().getTokenAttributes()
                        .get(JwtStaticClaim.USERNAME.getName())).toUpperCase();
    }

    private static JwtAuthenticationToken principalToken() {
        return (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    }

}
