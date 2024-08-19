package uz.pdp.gymproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.gymproject.entity.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByEmail(String email);
}