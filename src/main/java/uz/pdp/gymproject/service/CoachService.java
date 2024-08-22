package uz.pdp.gymproject.service;

import uz.pdp.gymproject.dto.CoachDto;
import uz.pdp.gymproject.entity.Coach;

public interface CoachService {

    Coach save(CoachDto coachDto);
}
