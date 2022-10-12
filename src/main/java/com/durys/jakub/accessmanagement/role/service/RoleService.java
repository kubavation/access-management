package com.durys.jakub.accessmanagement.role.service;

import com.durys.jakub.accessmanagement.role.exception.RoleWithNameAlreadyExistsException;
import com.durys.jakub.accessmanagement.role.model.dto.RoleDTO;
import com.durys.jakub.accessmanagement.role.model.entity.Role;
import com.durys.jakub.accessmanagement.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.durys.jakub.accessmanagement.role.mappers.RoleMapper.toDTO;
import static com.durys.jakub.accessmanagement.role.mappers.RoleMapper.toEntity;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public List<RoleDTO> findAll() {
        return toDTO(roleRepository.findAll());
    }

    public Role findByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(RuntimeException::new); //todo
    }

    public void create(RoleDTO roleDTO) {

        validateRole(roleDTO);

        Role entity = toEntity(roleDTO);
        entity.setCreatedAt(LocalDateTime.now());
        roleRepository.save(entity);
    }

    public void validateRole(RoleDTO roleDTO) {

        if (roleRepository.findByName(roleDTO.getName()).isPresent()) {
            throw new RoleWithNameAlreadyExistsException(roleDTO.getName());
        }

    }
}
