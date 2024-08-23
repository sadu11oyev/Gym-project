package uz.pdp.gymproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.gymproject.entity.Trainee;

import java.util.UUID;

public interface TraineeRepository extends JpaRepository<Trainee, UUID> {
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "select * from trainee where user_id =:id")
    Trainee findByUserId(UUID id);
}