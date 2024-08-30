package uz.pdp.gymproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.gymproject.dto.TraineeDto;
import uz.pdp.gymproject.model.request.TraineeReqDto;
import uz.pdp.gymproject.model.request.UpdateCoachList;
import uz.pdp.gymproject.model.response.CoachResDto;
import uz.pdp.gymproject.model.response.TraineeResDto;
import uz.pdp.gymproject.model.response.TraineeUpdateResDto;
import uz.pdp.gymproject.response.Response;
import uz.pdp.gymproject.service.DATA;
import uz.pdp.gymproject.service.TraineeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trainee")
public class TraineeController {
    private final TraineeService traineeService;

    @GetMapping()
    public HttpEntity<?> getTraineeProfile(@PathVariable String gmail){
        TraineeResDto traineeResDto = traineeService.getTraineeProfile(gmail);
        return ResponseEntity.ok(
                Response.builder().message("Trainee profile").data(traineeResDto).build()
        );
    }

    @GetMapping("register")
    public HttpEntity<List<String>> getAddresses(){return ResponseEntity.ok(DATA.districtList);
    }
    @PostMapping("register")
    public HttpEntity<?> saveTrainee(@RequestBody TraineeDto traineeDto){
        return ResponseEntity.ok(
                Response.builder().message("Register Data").data(traineeService.saveTrainee(traineeDto)).build()
        ) ;
    }

    @PostMapping("update")
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

    @PostMapping("updateCoachList")
    public List<CoachResDto> updateCoachList(@RequestBody UpdateCoachList updateCoachList){
        return traineeService.updateCoachList(updateCoachList);
    }


}
