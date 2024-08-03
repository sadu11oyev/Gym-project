package uz.pdp.gymproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.gymproject.dto.CoachDto;
import uz.pdp.gymproject.dto.TraineeDto;
import uz.pdp.gymproject.dto.TrainingTypeDto;
import uz.pdp.gymproject.response.Response;
import uz.pdp.gymproject.service.CoachService;
import uz.pdp.gymproject.service.DATA;
import uz.pdp.gymproject.service.TraineeService;
import uz.pdp.gymproject.service.TrainingTypeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final TraineeService traineeService;
    private final CoachService coachService;
    private final TrainingTypeService trainingTypeService;

    @GetMapping("coachRegister")
    public HttpEntity<List<TrainingTypeDto>> getRegisterPage(){
        return trainingTypeService.getTrainingTypes();
    }

    @PostMapping("coachRegister")
    public HttpEntity<?> saveCoach(@RequestBody CoachDto coachDto){
        System.out.println(coachDto.getTrainingTypeDto().name());
        return ResponseEntity.ok(
                Response.builder().message("Response").data(coachService.save(coachDto)).build()
        );
    }
    @GetMapping("traineeRegister")
    public HttpEntity<List<String>> getAddresses(){return ResponseEntity.ok(DATA.districtList);
    }
    @PostMapping("traineeRegister")
    public HttpEntity<?> saveTrainee(@RequestBody TraineeDto traineeDto){
        return ResponseEntity.ok(
                Response.builder().message("Register Data").data(traineeService.saveTrainee(traineeDto)).build()
        ) ;
    }
}
