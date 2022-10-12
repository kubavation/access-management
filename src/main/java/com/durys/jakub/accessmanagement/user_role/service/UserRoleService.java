package com.durys.jakub.accessmanagement.user_role.service;

import com.durys.jakub.accessmanagement.role.mappers.RoleMapper;
import com.durys.jakub.accessmanagement.role.model.dto.RoleDTO;
import com.durys.jakub.accessmanagement.user.model.dto.UserDTO;
import com.durys.jakub.accessmanagement.user.model.entity.User;
import com.durys.jakub.accessmanagement.user_role.model.dto.AddRolesToUserRequest;
import com.durys.jakub.accessmanagement.user_role.model.entity.UserRole;
import com.durys.jakub.accessmanagement.user_role.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    @Transactional
    public void addRolesToUser(List<RoleDTO> roles, User user) {

        userRoleRepository.deleteAllInBatch(
                userRoleRepository.findAllByUserId(user.getId()));

        try {
            roles.stream()
                    .distinct()
                    .map(RoleMapper::toEntity)
                    .forEach(role -> userRoleRepository.save(new UserRole(user, role)));
        } catch (Exception ex) {
            throw new RuntimeException("Role does not exists");
        }

    }

  
}
