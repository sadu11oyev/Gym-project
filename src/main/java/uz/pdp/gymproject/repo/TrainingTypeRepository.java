package uz.pdp.gymproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.gymproject.entity.TrainingType;

import java.util.Optional;
import java.util.UUID;

public interface TrainingTypeRepository extends JpaRepository<TrainingType, UUID> {
    Optional<TrainingType> findByName(String specializations);
}