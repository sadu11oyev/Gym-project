package uz.pdp.gymproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.gymproject.config.AuditorAwareImpl;
import uz.pdp.gymproject.model.request.TraineeReqDto;
import uz.pdp.gymproject.model.request.TraineeTrainingDto;
import uz.pdp.gymproject.model.request.UpdateCoachList;
import uz.pdp.gymproject.model.response.CoachResDto;
import uz.pdp.gymproject.model.response.TraineeResDto;
import uz.pdp.gymproject.model.response.TraineeTrainingResDto;
import uz.pdp.gymproject.model.response.TraineeUpdateResDto;
import uz.pdp.gymproject.response.Response;
import uz.pdp.gymproject.service.TraineeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trainee")
public class TraineeController {
    private final TraineeService traineeService;
    private final AuditorAwareImpl auditorAware;

    @GetMapping()
    public HttpEntity<?> getTraineeProfile(){
        TraineeResDto traineeResDto = traineeService.getTraineeProfile(auditorAware.getAuthenticatedUser());
        return ResponseEntity.ok(
                Response.builder().message("Trainee profile").data(traineeResDto).build()
        );
    }

    @PutMapping("update")
    public HttpEntity<?> updateTrainee(@RequestBody TraineeReqDto traineeReqDto){
        TraineeUpdateResDto traineeUpdateResDto = traineeService.getUpdateTraineeProfile(traineeReqDto);
        return ResponseEntity.ok(
                Response.builder().message("Update trainee profile").data(traineeUpdateResDto).build()
        );
    }

    @DeleteMapping("delete")
    public HttpEntity<?> deleteTrainee(@PathVariable String email){
        return ResponseEntity.ok(
                Response.builder().message("Delete trainee").data(traineeService.deleteTrainee(email)).build()
        );
    }

    @PutMapping("updateCoachList")
    public List<CoachResDto> updateCoachList(@RequestBody UpdateCoachList updateCoachList){
        return traineeService.updateCoachList(updateCoachList);
    }

    @GetMapping("getTrainingList")
    public TraineeTrainingResDto getTraineeTrainingList(@RequestBody TraineeTrainingDto traineeTrainingDto){
        return traineeService.getTrainingList(traineeTrainingDto);
    }


}
