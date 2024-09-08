package uz.pdp.gymproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.gymproject.entity.Coach;

import java.util.List;
import java.util.UUID;

public interface CoachRepository extends JpaRepository<Coach, UUID> {
    @Query(nativeQuery = true, value = "select coach_id from trainee_coach where trainee_id=:traineeId")
    List<UUID> findAllIdByTraineeId(@Param("traineeId") UUID traineeId);

    @Query(nativeQuery = true, value = "SELECT tt.name FROM coach c JOIN training_type tt ON c.training_type_id = tt.id WHERE c.id = :coachId")
    String findSpecializations(@Param("coachId") UUID coachId);

    @Query(nativeQuery = true, value = "select c.* from coach c where user_id =:userId ")
    Coach findByUserId(UUID userId);
}