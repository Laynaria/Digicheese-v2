package com.digicheese.digi_v2.seeds;

import com.digicheese.digi_v2.models.Role;
import com.digicheese.digi_v2.models.User;
import com.digicheese.digi_v2.repositories.RoleRepository;
import com.digicheese.digi_v2.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, RoleRepository roleRepository) {
        return args -> {

            Role adminRole = roleRepository.findByRoleLabel("Admin")
                    .orElseGet(() -> roleRepository.save(new Role(null, "Admin")));

            Role opColisRole = roleRepository.findByRoleLabel("Op-colis")
                    .orElseGet(() -> roleRepository.save(new Role(null, "Op-colis")));

            Role opStocksRole = roleRepository.findByRoleLabel("Op-stocks")
                    .orElseGet(() -> roleRepository.save(new Role(null, "Op-stocks")));

            if (userRepository.count() == 0) {
                User john = new User(null, "Red", "John", "T@1234", "john@test.com");
                Set<Role> johnRoles = new HashSet<>();
                johnRoles.add(adminRole);
                john.setRoles(johnRoles);

                User anna = new User(null, "Black", "Anna", "T2@abcd", "anna@test.com");
                Set<Role> annaRoles = new HashSet<>();
                annaRoles.add(opColisRole);
                anna.setRoles(annaRoles);

                User mike = new User(null, "White", "Mike", "T3@xyz", "mike@test.com");
                Set<Role> mikeRoles = new HashSet<>();
                mikeRoles.add(opStocksRole);
                mike.setRoles(mikeRoles);

                userRepository.save(john);
                userRepository.save(anna);
                userRepository.save(mike);
            }
        };
    }
}