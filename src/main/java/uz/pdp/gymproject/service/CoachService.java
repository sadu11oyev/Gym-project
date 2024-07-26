package uz.pdp.gymproject.service;

import uz.pdp.gymproject.dto.CoachDto;
import uz.pdp.gymproject.model.request.CoachUpdateReqDto;
import uz.pdp.gymproject.model.response.dto.CoachResDto2;

public interface CoachService {

    String save(CoachDto coachDto);

    CoachResDto2 getCoachProfile(String email);

    CoachResDto2 updateCoach(CoachUpdateReqDto coachUpdateReqDto);
}
