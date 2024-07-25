package uz.pdp.gymproject.service;

import org.springframework.http.HttpEntity;
import uz.pdp.gymproject.dto.CoachDto;

public interface CoachService {

    String save(CoachDto coachDto);

    HttpEntity<?> getCoachProfile(String email);
}
