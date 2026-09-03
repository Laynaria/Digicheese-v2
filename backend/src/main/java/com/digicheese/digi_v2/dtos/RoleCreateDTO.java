package com.digicheese.digi_v2.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RoleCreateDTO {

    @NotBlank(message = "Le libelle du role est obligatoire")
    @Size(max = 100, message = "Le libelle du role ne peut pas depasser 100 caracteres")
    private String roleLabel;

    public RoleCreateDTO() {
    }

    public String getRoleLabel() {
        return roleLabel;
    }
}
