package com.durys.jakub.accessmanagement.keycloak.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
enum JwtStaticClaim {

    USERNAME("preferred_username");

    private final String name;
}
