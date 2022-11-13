package com.durys.jakub.accessmanagement.user.mapper;

import com.durys.jakub.accessmanagement.user.model.dto.UserDTO;
import com.durys.jakub.accessmanagement.user.model.dto.UserDetailsDTO;
import com.durys.jakub.accessmanagement.user.model.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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


    public static UserDetailsDTO toDetailsDTO(UserRepresentation user) {

        return UserDetailsDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .createdAt(LocalDateTime
                        .ofInstant(
                                Instant.ofEpochMilli(user.getCreatedTimestamp()), ZoneId.systemDefault()))
                .build();
    }

    public static List<UserDTO> toDTO(List<UserRepresentation> entities) {
        return entities.stream().map(UserMapper::toDTO).toList();
    }

    public static UserDTO toDTO(UserRepresentation user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail());
    }

}
