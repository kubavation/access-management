package com.durys.jakub.accessmanagement.user_role.service;

import com.durys.jakub.accessmanagement.user_role.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;
}
