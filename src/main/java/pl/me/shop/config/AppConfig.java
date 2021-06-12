package pl.me.shop.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.me.shop.model.dao.Role;
import pl.me.shop.repository.RoleRepository;

import java.util.Optional;


@Configuration
public class AppConfig {

    @Bean
    public CommandLineRunner commandLineRunner(RoleRepository roleRepository){
        return args -> {
            Optional<Role> roleUser = roleRepository.findByName("ROLE_USER");
            Optional<Role> roleAdmin = roleRepository.findByName("ROLE_ADMIN");

            if (roleUser.isEmpty()) {
                roleRepository.save(Role.builder().name("ROLE_USER")
                        .build());
            }
            if (roleAdmin.isEmpty()) {
                roleRepository.save(Role.builder().name("ROLE_ADMIN")
                        .build());
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
