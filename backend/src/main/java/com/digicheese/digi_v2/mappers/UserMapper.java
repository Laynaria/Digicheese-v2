package com.digicheese.digi_v2.mappers;

import com.digicheese.digi_v2.dtos.UserCreateDTO;
import com.digicheese.digi_v2.dtos.UserDTO;
import com.digicheese.digi_v2.models.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail()
        );
    }

    public static User toEntity(UserCreateDTO dto) {
        User user = new User();
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }
}