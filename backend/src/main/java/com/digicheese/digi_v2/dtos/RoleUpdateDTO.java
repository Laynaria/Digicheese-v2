package com.digicheese.digi_v2.dtos;

import jakarta.validation.constraints.Size;

public class RoleUpdateDTO {

    @Size(max = 100, message = "Le libelle du role ne peut pas depasser 100 caracteres")
    private String roleLabel;

    public String getRoleLabel() {
        return roleLabel;
    }
}
