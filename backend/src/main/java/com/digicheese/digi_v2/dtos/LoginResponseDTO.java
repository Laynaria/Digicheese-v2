package com.digicheese.digi_v2.dtos;

public class LoginResponseDTO {

    private String token;
    private long expiresIn;

    public LoginResponseDTO() {}

    public LoginResponseDTO(String token, long expiresIn) {
        this.token = token;
        this.expiresIn = expiresIn;
    }

    public String getToken() {
        return token;
    }

    public long getExpiresIn() {
        return expiresIn;
    }
}
