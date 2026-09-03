package com.digicheese.digi_v2.seeds;

import com.digicheese.digi_v2.models.User;
import com.digicheese.digi_v2.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {

            if (userRepository.findByEmail("john@test.com").isEmpty()) {
                userRepository.save(
                        new User(null, "Red", "John", passwordEncoder.encode("T@1234"), "john@test.com")
                );
            }

            if (userRepository.findByEmail("anna@test.com").isEmpty()) {
                userRepository.save(
                        new User(null, "Black", "Anna", passwordEncoder.encode("T2@abcd"), "anna@test.com")
                );
            }
        };
    }
}
