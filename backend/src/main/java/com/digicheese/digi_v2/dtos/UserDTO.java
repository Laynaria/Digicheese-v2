package com.digicheese.digi_v2.dtos;

public class UserDTO {

    private Integer id;
    private String firstname;
    private String lastname;
    private String email;

    public UserDTO() {}

    public UserDTO(Integer id, String firstname, String lastname, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }
}