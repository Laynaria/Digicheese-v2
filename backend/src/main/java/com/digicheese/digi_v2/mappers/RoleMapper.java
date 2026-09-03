package com.digicheese.digi_v2.mappers;

import com.digicheese.digi_v2.dtos.RoleCreateDTO;
import com.digicheese.digi_v2.dtos.RoleDTO;
import com.digicheese.digi_v2.models.Role;

public class RoleMapper {

    public static RoleDTO toDTO(Role role) {
        return new RoleDTO(
                role.getId(),
                role.getRoleLabel()
        );
    }

    public static Role toEntity(RoleCreateDTO dto) {
        Role role = new Role();
        role.setRoleLabel(dto.getRoleLabel());
        return role;
    }
}