package uz.pdp.gymproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.gymproject.entity.Training;

import java.time.LocalDate;
import java.util.UUID;

public interface TrainingRepository extends JpaRepository<Training, UUID> {
    @Query(nativeQuery = true,value = "select * from training t where coach_id =:coachId and training_type_id =: trainingTypeId and trainee_id =: traineeId")
    Training findByAllReferences(UUID coachId, UUID trainingTypeId, UUID traineeId, LocalDate from, LocalDate traineeTrainingDtoTo);
}