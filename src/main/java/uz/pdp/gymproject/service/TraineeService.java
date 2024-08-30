package uz.pdp.gymproject.service;

import org.springframework.http.HttpEntity;
import uz.pdp.gymproject.dto.TraineeDto;
import uz.pdp.gymproject.model.request.TraineeReqDto;
import uz.pdp.gymproject.model.request.UpdateCoachList;
import uz.pdp.gymproject.model.response.CoachResDto;
import uz.pdp.gymproject.model.response.TraineeResDto;
import uz.pdp.gymproject.model.response.TraineeUpdateResDto;

import java.util.List;

public interface TraineeService {

    String saveTrainee(TraineeDto traineeDto);

    TraineeResDto getTraineeProfile(String gmail);

    TraineeUpdateResDto getUpdateTraineeProfile(TraineeReqDto traineeReqDto);

    String deleteTrainee(String email);

    List<CoachResDto> updateCoachList(UpdateCoachList updateCoachList);
}
