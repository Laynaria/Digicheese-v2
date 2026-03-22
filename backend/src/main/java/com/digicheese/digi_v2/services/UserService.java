package com.digicheese.digi_v2.services;

import com.digicheese.digi_v2.dtos.UserCreateDTO;
import com.digicheese.digi_v2.dtos.UserDTO;
import com.digicheese.digi_v2.mappers.UserMapper;
import com.digicheese.digi_v2.models.User;
import com.digicheese.digi_v2.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(UserCreateDTO dto) {

        User user = UserMapper.toEntity(dto);

        User saved = userRepository.save(user);

        return UserMapper.toDTO(saved);
    }

    public UserDTO getByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        return UserMapper.toDTO(user);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .toList();
    }
}