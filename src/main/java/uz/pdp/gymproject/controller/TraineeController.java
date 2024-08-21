package uz.pdp.gymproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.gymproject.dto.TraineeDto;
import uz.pdp.gymproject.entity.Trainee;
import uz.pdp.gymproject.service.DATA;
import uz.pdp.gymproject.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trainee")
public class TraineeController {
    private final UserService userService;

    @GetMapping("register")
    ResponseEntity<List<String>> getAddresses(){return (ResponseEntity<List<String>>) List.of(DATA.districtList);
    }
    @PostMapping("register")
    private ResponseEntity<Trainee> saveTrainee(@RequestBody TraineeDto traineeDto){
        return userService.saveTrainee(traineeDto);
    }


}
