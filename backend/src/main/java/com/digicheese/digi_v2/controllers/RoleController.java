package com.digicheese.digi_v2.controllers;

import com.digicheese.digi_v2.dtos.RoleCreateDTO;
import com.digicheese.digi_v2.dtos.RoleDTO;
import com.digicheese.digi_v2.dtos.RoleUpdateDTO;
import com.digicheese.digi_v2.services.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public List<RoleDTO> getRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public RoleDTO getRoleById(@PathVariable Integer id) {
        return roleService.getById(id);
    }

    @GetMapping("/by-label")
    public RoleDTO getRoleByLabel(@RequestParam String roleLabel) {
        return roleService.getByRoleLabel(roleLabel);
    }

    @PostMapping
    public RoleDTO createRole(@RequestBody RoleCreateDTO dto) {
        return roleService.createRole(dto);
    }

    @PatchMapping("/{id}")
    public RoleDTO updateRole(@PathVariable Integer id, @RequestBody RoleUpdateDTO dto) {
        return roleService.updateRole(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Integer id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}