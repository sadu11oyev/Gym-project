package uz.pdp.gymproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.gymproject.dto.TraineeDto;
import uz.pdp.gymproject.entity.Trainee;
import uz.pdp.gymproject.service.DATA;
import uz.pdp.gymproject.service.TraineeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trainee")
public class TraineeController {
    private final TraineeService traineeService;

    @GetMapping("register")
    public ResponseEntity<List<String>> getAddresses(){return ResponseEntity.ok(DATA.districtList);
    }
    @PostMapping("register")
    public ResponseEntity<Trainee> saveTrainee(@RequestBody TraineeDto traineeDto){
        return traineeService.saveTrainee(traineeDto);
    }


}
