package uz.pdp.gymproject.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.pdp.gymproject.entity.User;
import uz.pdp.gymproject.repo.UserRepository;

@Component
@RequiredArgsConstructor
public class AuditorAwareImpl {
    private final UserRepository userRepository;

    public User getAuthenticatedUser() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(email);
    }
}
