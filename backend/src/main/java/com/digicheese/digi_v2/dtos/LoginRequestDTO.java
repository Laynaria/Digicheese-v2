package com.digicheese.digi_v2.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequestDTO {

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email est mal forme")
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire")
    private String password;

    public String getEmail() { return email; }
    public String getPassword() { return password; }
}
