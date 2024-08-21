package uz.pdp.gymproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.gymproject.entity.Trainee;

import java.util.UUID;

public interface TraineeRepository extends JpaRepository<Trainee, UUID> {
}