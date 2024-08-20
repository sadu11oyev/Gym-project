package uz.pdp.gymproject.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.gymproject.entity.Role;
import uz.pdp.gymproject.entity.User;
import uz.pdp.gymproject.entity.enums.RoleName;
import uz.pdp.gymproject.repo.RoleRepository;
import uz.pdp.gymproject.repo.UserRepository;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;
    @Override
    public void run(String... args) throws Exception {
        generateAdmin();
    }
    private void generateAdmin() throws IOException {
        if (ddl.equals("create")){
            Role roleAdmin = new Role(1, RoleName.ROLE_ADMIN);
            Role roleManager = new Role(2, RoleName.ROLE_COACH);
            Role roleUser = new Role(3, RoleName.ROLE_USER);
            roleRepository.saveAll(List.of(roleAdmin,roleManager,roleUser));

            User admin = User.builder()
                    .firstName("Baxtiyor")
                    .lastName("Sadulloyev")
                    .phone("97 864 44 00")
                    .email("baxti@gmail.com")
                    .password(passwordEncoder.encode("root123"))
                    .roles(List.of(roleAdmin,roleManager))
                    .build();
            userRepository.save(admin);
        }
    }
}
