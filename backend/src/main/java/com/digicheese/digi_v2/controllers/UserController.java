package com.digicheese.digi_v2.controllers;

import com.digicheese.digi_v2.dtos.UserCreateDTO;
import com.digicheese.digi_v2.dtos.UserDTO;
import com.digicheese.digi_v2.dtos.UserUpdateDTO;
import com.digicheese.digi_v2.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @GetMapping("/by-email")
    public UserDTO getByEmail(@RequestParam String email) {
        return userService.getByEmail(email);
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserCreateDTO dto) {
        return userService.createUser(dto);
    }

    @PatchMapping("/{id}")
    public UserDTO updateUser(@PathVariable Integer id, @RequestBody UserUpdateDTO dto) {
        return userService.updateUser(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}