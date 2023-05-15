package com.durys.jakub.accessmanagement.shared.keycloak.token;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
enum JwtStaticClaim {

    USERNAME("preferred_username");

    private final String name;
}
