package uz.pdp.gymproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.gymproject.entity.Trainee;
import uz.pdp.gymproject.entity.TrainingType;

import java.util.UUID;

public interface TraineeRepository extends JpaRepository<Trainee, UUID> {
    @Transactional
    @Query(nativeQuery = true, value = "select * from trainee where user_id =:id")
    Trainee findByUserId(UUID id);

    @Transactional
    @Query(nativeQuery = true, value = "select * from training_type where name=:specialization")
    TrainingType findByName(String specialization);
}