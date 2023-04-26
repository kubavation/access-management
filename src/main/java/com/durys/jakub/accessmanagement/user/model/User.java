package com.durys.jakub.accessmanagement.user.model;

import com.durys.jakub.accessmanagement.role.model.Role;
import lombok.Data;
import lombok.NonNull;

import java.util.Collections;
import java.util.Set;

@Data
public class User {
    private String id;
    private String username;
    private String email;
    private boolean enabled;
    private Set<Role> roles;

    public User(String id, String username, String email, boolean enabled, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.enabled = enabled;
        this.roles = roles;
    }

    public User(String id, String username, String email, boolean enabled) {
        this(id, username, email, enabled, Collections.emptySet());
    }

    public void setRoles(@NonNull Set<Role> roles) {
        this.roles = roles;
    }
}
