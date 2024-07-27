package uz.pdp.gymproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.gymproject.entity.Training;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface TrainingRepository extends JpaRepository<Training, UUID> {
    @Query(nativeQuery = true, value = "select * from training t where t.coach_id = :coachId and t.training_type_id = :trainingTypeId and t.trainee_id = :traineeId and t.date >= :startDate and t.date + t.duration * INTERVAL '1 day' <= :endDate")
    Optional<Training> findByAllReferences(UUID coachId, UUID trainingTypeId, UUID traineeId, @Param("startDate") LocalDate from, @Param("endDate") LocalDate to);


}