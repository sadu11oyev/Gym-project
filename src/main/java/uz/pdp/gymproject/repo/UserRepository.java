package uz.pdp.gymproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.gymproject.entity.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByEmail(String email);

    @Query(nativeQuery = true, value = "select u.* from users u join trainee t on u.id = t.user_id where t.id =: traineeId and u.is_active=true")
    User findByTraieeIdIAndActive(UUID traineeId);
}