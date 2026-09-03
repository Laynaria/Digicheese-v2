package com.digicheese.digi_v2.dtos;

public class RoleDTO {

    private Integer id;
    private String roleLabel;

    public RoleDTO() {
    }

    public RoleDTO(Integer id, String roleLabel) {
        this.id = id;
        this.roleLabel = roleLabel;
    }

    public Integer getId() {
        return id;
    }

    public String getRoleLabel() {
        return roleLabel;
    }
}