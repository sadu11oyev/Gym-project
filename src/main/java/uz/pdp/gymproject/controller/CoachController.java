package uz.pdp.gymproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.gymproject.dto.CoachDto;
import uz.pdp.gymproject.dto.TrainingTypeDto;
import uz.pdp.gymproject.entity.Coach;
import uz.pdp.gymproject.service.CoachService;
import uz.pdp.gymproject.service.TrainingTypeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coach")
public class CoachController {
    private final TrainingTypeService trainingTypeService;
    private final CoachService coachService;

    @GetMapping("register")
    public ResponseEntity<List<TrainingTypeDto>> getRegisterPage(){
        return trainingTypeService.getTrainingTypes();
    }

    @PostMapping("register")
    public ResponseEntity<Coach> saveCoach(@RequestBody CoachDto coachDto){
        return ResponseEntity.ok(coachService.save(coachDto));
    }
}
