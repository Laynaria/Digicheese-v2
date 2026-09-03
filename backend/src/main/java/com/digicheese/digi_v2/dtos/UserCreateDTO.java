package com.digicheese.digi_v2.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserCreateDTO {

    @NotBlank(message = "Le prenom est obligatoire")
    @Size(max = 100, message = "Le prenom ne peut pas depasser 100 caracteres")
    private String firstname;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 100, message = "Le nom ne peut pas depasser 100 caracteres")
    private String lastname;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email est mal forme")
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(min = 8, message = "Le mot de passe doit contenir au moins 8 caracteres")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).+$",
             message = "Le mot de passe doit contenir une minuscule, une majuscule, un chiffre et un caractere special")
    private String password;

    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}
