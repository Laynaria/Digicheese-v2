package com.digicheese.digi_v2.services;

import com.digicheese.digi_v2.dtos.RoleCreateDTO;
import com.digicheese.digi_v2.dtos.RoleDTO;
import com.digicheese.digi_v2.dtos.RoleUpdateDTO;
import com.digicheese.digi_v2.mappers.RoleMapper;
import com.digicheese.digi_v2.models.Role;
import com.digicheese.digi_v2.models.User;
import com.digicheese.digi_v2.repositories.RoleRepository;
import com.digicheese.digi_v2.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public RoleService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public RoleDTO createRole(RoleCreateDTO dto) {
        if (dto == null || dto.getRoleLabel() == null || dto.getRoleLabel().trim().isEmpty()) {
            throw new RuntimeException("Role label is required");
        }

        String normalizedRoleLabel = dto.getRoleLabel().trim();

        if (roleRepository.existsByRoleLabel(normalizedRoleLabel)) {
            throw new RuntimeException("Role already exists with label: " + normalizedRoleLabel);
        }

        Role role = RoleMapper.toEntity(dto);
        role.setRoleLabel(normalizedRoleLabel);

        Role savedRole = roleRepository.save(role);

        return RoleMapper.toDTO(savedRole);
    }

    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(RoleMapper::toDTO)
                .toList();
    }

    public RoleDTO getById(Integer id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));

        return RoleMapper.toDTO(role);
    }

    public RoleDTO getByRoleLabel(String roleLabel) {
        if (roleLabel == null || roleLabel.trim().isEmpty()) {
            throw new RuntimeException("Role label is required");
        }

        String normalizedRoleLabel = roleLabel.trim();

        Role role = roleRepository.findByRoleLabel(normalizedRoleLabel)
                .orElseThrow(() -> new RuntimeException("Role not found with label: " + normalizedRoleLabel));

        return RoleMapper.toDTO(role);
    }

    public RoleDTO updateRole(Integer id, RoleUpdateDTO dto) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));

        if (dto.getRoleLabel() != null && !dto.getRoleLabel().trim().isEmpty()) {
            String normalizedRoleLabel = dto.getRoleLabel().trim();

            roleRepository.findByRoleLabel(normalizedRoleLabel)
                    .ifPresent(existingRole -> {
                        if (!existingRole.getId().equals(role.getId())) {
                            throw new RuntimeException("Role already exists with label: " + normalizedRoleLabel);
                        }
                    });

            role.setRoleLabel(normalizedRoleLabel);
        }

        Role updatedRole = roleRepository.save(role);
        return RoleMapper.toDTO(updatedRole);
    }

    @Transactional
    public void deleteRole(Integer id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));

        Set<User> users = new HashSet<>(role.getUsers());

        for (User user : users) {
            user.getRoles().remove(role);
            userRepository.save(user);
        }

        roleRepository.delete(role);
    }
}