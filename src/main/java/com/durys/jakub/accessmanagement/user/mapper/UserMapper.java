package com.durys.jakub.accessmanagement.user.mapper;

import com.durys.jakub.accessmanagement.user.model.dto.UserDTO;
import com.durys.jakub.accessmanagement.user.model.dto.UserDetailsDTO;
import com.durys.jakub.accessmanagement.user.model.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;
import java.util.stream.Collectors;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    public static User toEntity(UserDTO dto) {
        return User.builder()
                //.id(dto.getId())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .build();
    }


    public static UserDetailsDTO toDetailsDTO(User entity) {
        return UserDetailsDTO.builder()
                .username(entity.getUsername())
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }

    public static List<UserDTO> toDTO(List<UserRepresentation> entities) {
        return entities.stream().map(UserMapper::toDTO).toList();
    }

    public static UserDTO toDTO(UserRepresentation user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail());
    }

}
