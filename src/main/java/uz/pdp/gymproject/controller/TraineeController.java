package uz.pdp.gymproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.gymproject.dto.TraineeDto;
import uz.pdp.gymproject.entity.Trainee;
import uz.pdp.gymproject.entity.User;
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
    public HttpEntity<?> getTraineeProfile(String gmail){
        TraineeDto trainee = traineeService.getTraineeProfile(gmail);
        return ResponseEntity.ok(
                Response.builder().message("Trainee profile").data(trainee).build()
        );
    }

    @GetMapping("register")
    public HttpEntity<List<String>> getAddresses(){return ResponseEntity.ok(DATA.districtList);
    }
    @PostMapping("register")
    public HttpEntity<Trainee> saveTrainee(@RequestBody TraineeDto traineeDto){
        return traineeService.saveTrainee(traineeDto);
    }

//    @PostMapping("update")
//    public HttpEntity<?> updateTrainee(@RequestBody )


}
