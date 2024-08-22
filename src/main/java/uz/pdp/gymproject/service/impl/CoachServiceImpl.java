package uz.pdp.gymproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.gymproject.dto.CoachDto;
import uz.pdp.gymproject.entity.Coach;
import uz.pdp.gymproject.service.CoachService;

@Service
@RequiredArgsConstructor
public class CoachServiceImpl implements CoachService {
    @Override
    public Coach save(CoachDto coachDto) {
        return null;
    }
}
