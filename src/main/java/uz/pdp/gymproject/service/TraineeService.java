package uz.pdp.gymproject.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.gymproject.dto.TraineeDto;
import uz.pdp.gymproject.entity.Trainee;
import uz.pdp.gymproject.model.request.TraineeReqDto;
import uz.pdp.gymproject.model.response.TraineeResDto;
import uz.pdp.gymproject.model.response.TraineeUpdateResDto;

public interface TraineeService {

    ResponseEntity<Trainee> saveTrainee(TraineeDto traineeDto);

    TraineeResDto getTraineeProfile(String gmail);

    TraineeUpdateResDto getUpdateTraineeProfile(TraineeReqDto traineeReqDto);

    String deleteTrainee(String email);
}
