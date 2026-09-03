package com.digicheese.digi_v2.seeds;

import com.digicheese.digi_v2.models.Role;
import com.digicheese.digi_v2.models.User;
import com.digicheese.digi_v2.repositories.RoleRepository;
import com.digicheese.digi_v2.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Set;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository,
                                   RoleRepository roleRepository,
                                   PasswordEncoder passwordEncoder) {
        return args -> {

            Role adminRole = roleRepository.findByRoleLabel("Admin")
                    .orElseGet(() -> roleRepository.save(new Role(null, "Admin")));

            Role opColisRole = roleRepository.findByRoleLabel("Op-colis")
                    .orElseGet(() -> roleRepository.save(new Role(null, "Op-colis")));

            Role opStocksRole = roleRepository.findByRoleLabel("Op-stocks")
                    .orElseGet(() -> roleRepository.save(new Role(null, "Op-stocks")));

            if (userRepository.findByEmail("john@test.com").isEmpty()) {
                User john = new User(null, "Red", "John", passwordEncoder.encode("T@1234"), "john@test.com");
                john.setRoles(Set.of(adminRole));
                userRepository.save(john);
            }

            if (userRepository.findByEmail("anna@test.com").isEmpty()) {
                User anna = new User(null, "Black", "Anna", passwordEncoder.encode("T2@abcd"), "anna@test.com");
                anna.setRoles(Set.of(opColisRole));
                userRepository.save(anna);
            }

            if (userRepository.findByEmail("mike@test.com").isEmpty()) {
                User mike = new User(null, "White", "Mike", passwordEncoder.encode("T3@xyz"), "mike@test.com");
                mike.setRoles(Set.of(opStocksRole));
                userRepository.save(mike);
            }
        };
    }
}
