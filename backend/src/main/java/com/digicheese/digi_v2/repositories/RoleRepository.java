package com.digicheese.digi_v2.repositories;

import com.digicheese.digi_v2.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByRoleLabel(String roleLabel);

    boolean existsByRoleLabel(String roleLabel);
}