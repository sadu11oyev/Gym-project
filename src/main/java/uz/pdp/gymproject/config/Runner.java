package uz.pdp.gymproject.config;

import lombok.RequiredArgsConstructor;
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

//    @Value("${spring.jpa.hibernate.ddl-auto}")
//    private String ddl;
    @Override
    public void run(String... args) throws Exception {
//        generateAdmin();
    }
    private void generateAdmin() throws IOException {
        Role roleAdmin = new Role(1, RoleName.ROLE_ADMIN);
        Role roleCoach = new Role(2, RoleName.ROLE_COACH);
        Role roleTrainee = new Role(3, RoleName.ROLE_TRAINEE);
        roleRepository.saveAll(List.of(roleAdmin,roleCoach,roleTrainee));

        User admin = User.builder()
                .firstName("Baxtiyor")
                .lastName("Sadulloyev")
                .phone("97 864 44 00")
                .username("baxti@gmail.com")
                .password(passwordEncoder.encode("root123"))
                .isActive(true)
                .roles(List.of(roleAdmin,roleCoach))
                .build();
        userRepository.save(admin);
//        if (ddl.equals("create")){
//
//        }
    }
}
