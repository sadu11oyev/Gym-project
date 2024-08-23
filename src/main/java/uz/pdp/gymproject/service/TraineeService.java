package uz.pdp.gymproject.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.gymproject.dto.TraineeDto;
import uz.pdp.gymproject.entity.Trainee;
import uz.pdp.gymproject.entity.User;

public interface TraineeService {

    ResponseEntity<Trainee> saveTrainee(TraineeDto traineeDto);

    TraineeDto getTraineeProfile(String gmail);
}
