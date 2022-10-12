package com.durys.jakub.accessmanagement.user.mapper;

import com.durys.jakub.accessmanagement.user.model.dto.UserDTO;
import com.durys.jakub.accessmanagement.user.model.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    public static User toEntity(UserDTO dto) {
        return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .build();
    }

    public static UserDTO toDTO(User entity) {
        return new UserDTO(entity.getId(), entity.getUsername());
    }


}
