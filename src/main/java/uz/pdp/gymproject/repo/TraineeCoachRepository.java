package uz.pdp.gymproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.gymproject.entity.TraineeCoach;

import java.util.List;
import java.util.UUID;

public interface TraineeCoachRepository extends JpaRepository<TraineeCoach, UUID> {
    @Query(nativeQuery = true, value = "select trc.trainee_id from trainee_coach trc where coach_id =: coachId ")
    List<UUID> getTraineeIdByCoachId(UUID coachId);
}