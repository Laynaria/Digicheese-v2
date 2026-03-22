package com.digicheese.digi_v2.seeds;

import com.digicheese.digi_v2.models.User;
import com.digicheese.digi_v2.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {

            if (userRepository.count() == 0) {

                userRepository.save(
                        new User(null, "Red", "John", "T@1234", "john@test.com")
                );

                userRepository.save(
                        new User(null, "Black", "Anna", "T2@abcd", "anna@test.com")
                );
            }
        };
    }
}