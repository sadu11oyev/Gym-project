package uz.pdp.gymproject.service;

import uz.pdp.gymproject.dto.CoachDto;
import uz.pdp.gymproject.entity.Coach;
import uz.pdp.gymproject.entity.User;
import uz.pdp.gymproject.model.request.CoachUpdateReqDto;
import uz.pdp.gymproject.model.response.CoachResDto;
import uz.pdp.gymproject.model.response.dto.CoachResDto2;

public interface CoachService {

    String save(CoachDto coachDto);

    CoachResDto2 getCoachProfile(User user);

    CoachResDto2 updateCoach(CoachUpdateReqDto coachUpdateReqDto);
    CoachResDto generateCoachResDto(Coach coach);
}
