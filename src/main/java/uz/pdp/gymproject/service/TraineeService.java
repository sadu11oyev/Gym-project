package uz.pdp.gymproject.service;

import uz.pdp.gymproject.dto.TraineeDto;
import uz.pdp.gymproject.entity.User;
import uz.pdp.gymproject.model.request.AddTrainingReqDto;
import uz.pdp.gymproject.model.request.TraineeReqDto;
import uz.pdp.gymproject.model.request.TraineeTrainingDto;
import uz.pdp.gymproject.model.request.UpdateCoachList;
import uz.pdp.gymproject.model.response.CoachResDto;
import uz.pdp.gymproject.model.response.TraineeResDto;
import uz.pdp.gymproject.model.response.TraineeTrainingResDto;
import uz.pdp.gymproject.model.response.TraineeUpdateResDto;

import java.util.List;

public interface TraineeService {

    String saveTrainee(TraineeDto traineeDto);

    TraineeResDto getTraineeProfile(User user);

    TraineeUpdateResDto getUpdateTraineeProfile(TraineeReqDto traineeReqDto);

    String deleteTrainee(String email);

    List<CoachResDto> updateCoachList(UpdateCoachList updateCoachList);

    TraineeTrainingResDto getTrainingList(TraineeTrainingDto traineeTrainingDto);

    String addTraining(AddTrainingReqDto addTrainingReqDto);
}
