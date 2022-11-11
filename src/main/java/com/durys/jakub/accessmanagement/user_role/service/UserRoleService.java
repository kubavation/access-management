package com.durys.jakub.accessmanagement.user_role.service;

import com.durys.jakub.accessmanagement.role.mappers.RoleMapper;
import com.durys.jakub.accessmanagement.role.model.dto.RoleDTO;
import com.durys.jakub.accessmanagement.role.model.entity.Role;
import com.durys.jakub.accessmanagement.user.mapper.UserMapper;
import com.durys.jakub.accessmanagement.user.model.dto.UserDTO;
import com.durys.jakub.accessmanagement.user.model.entity.User;
import com.durys.jakub.accessmanagement.user_role.model.entity.UserRole;
import com.durys.jakub.accessmanagement.user_role.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;


    public List<UserRole> getUserRoles(User user) {
        return userRoleRepository.findAllByUserId(user.getId());
    }

    @Transactional
    public void addRolesToUser(List<RoleDTO> roles, User user) {

        userRoleRepository.deleteAllInBatch(
                userRoleRepository.findAllByUserId(user.getId()));

        if (CollectionUtils.isEmpty(roles)) {
            return;
        }

        try {
            roles.stream()
                    .distinct()
                    .map(RoleMapper::toEntity)
                    .forEach(role -> userRoleRepository.save(new UserRole(user, role)));
        } catch (Exception ex) {
            throw new RuntimeException("Role does not exists");
        }

    }

    @Transactional
    public void addUsersToRole(List<UserDTO> users, Role role) {

        userRoleRepository.deleteAllInBatch(
                userRoleRepository.findAllByRoleName(role.getName()));

        try {
            users.stream()
                    .distinct()
                    .map(UserMapper::toEntity)
                    .forEach(user -> userRoleRepository.save(new UserRole(user, role)));
        } catch (Exception ex) {
            throw new RuntimeException("User does not exists");
        }

    }
}
