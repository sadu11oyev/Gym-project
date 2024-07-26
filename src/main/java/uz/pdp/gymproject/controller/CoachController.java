package uz.pdp.gymproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.gymproject.dto.CoachDto;
import uz.pdp.gymproject.dto.TrainingTypeDto;
import uz.pdp.gymproject.model.response.dto.CoachResDto2;
import uz.pdp.gymproject.response.Response;
import uz.pdp.gymproject.service.CoachService;
import uz.pdp.gymproject.service.TrainingTypeService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coach")
public class CoachController {
    private final TrainingTypeService trainingTypeService;
    private final CoachService coachService;

    @GetMapping()
    public HttpEntity<?> getCoachProfile(@RequestParam String email){
        return ResponseEntity.ok(
                Response.builder().message("Coach profile: ").data(coachService.getCoachProfile(email)).build()
        );
    }
    @GetMapping("register")
    public HttpEntity<List<TrainingTypeDto>> getRegisterPage(){
        return trainingTypeService.getTrainingTypes();
    }

    @PostMapping("register")
    public HttpEntity<?> saveCoach(@RequestBody CoachDto coachDto){
        return ResponseEntity.ok(
                Response.builder().message("Response").data(coachService.save(coachDto)).build()
        );
    }
}
