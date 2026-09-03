package com.digicheese.digi_v2.controllers;

import com.digicheese.digi_v2.dtos.LoginRequestDTO;
import com.digicheese.digi_v2.dtos.LoginResponseDTO;
import com.digicheese.digi_v2.models.User;
import com.digicheese.digi_v2.services.AuthenticationService;
import com.digicheese.digi_v2.services.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    public AuthenticationController(AuthenticationService authenticationService, JwtService jwtService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        User authenticatedUser = authenticationService.authenticate(loginRequest);
        String token = jwtService.generateToken(authenticatedUser);

        return ResponseEntity.ok(new LoginResponseDTO(token, jwtService.getExpirationTime()));
    }
}
