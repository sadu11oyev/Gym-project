package uz.pdp.gymproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.gymproject.config.AuditorAwareImpl;
import uz.pdp.gymproject.model.request.CoachUpdateReqDto;
import uz.pdp.gymproject.model.response.dto.CoachResDto2;
import uz.pdp.gymproject.response.Response;
import uz.pdp.gymproject.service.CoachService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coach")
public class CoachController {
    private final CoachService coachService;
    private final AuditorAwareImpl auditorAware;

    @GetMapping()
    public HttpEntity<?> getCoachProfile(){
        return ResponseEntity.ok(
                Response.builder().message("Coach profile: ").data(coachService.getCoachProfile(auditorAware.getAuthenticatedUser())).build()
        );
    }

    @PutMapping("update")
    public CoachResDto2 updateCoach(@RequestBody CoachUpdateReqDto coachUpdateReqDto){
        return coachService.updateCoach(coachUpdateReqDto);
    }
}
