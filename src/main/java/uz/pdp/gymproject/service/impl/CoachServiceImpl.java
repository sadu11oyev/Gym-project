package uz.pdp.gymproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import uz.pdp.gymproject.dto.CoachDto;
import uz.pdp.gymproject.entity.Coach;
import uz.pdp.gymproject.mappers.CoachMapper;
import uz.pdp.gymproject.repo.CoachRepository;
import uz.pdp.gymproject.service.AuthService;
import uz.pdp.gymproject.service.CoachService;
import uz.pdp.gymproject.service.TrainingTypeService;

@Service
@RequiredArgsConstructor
public class CoachServiceImpl implements CoachService {
    private final CoachRepository coachRepository;
    private final CoachMapper coachMapper;
    private final AuthService authService;
    private final TrainingTypeService trainingTypeService;

    @Override
    public String save(CoachDto coachDto) {
        authService.register(coachDto.getRegisterDto());
        trainingTypeService.save(coachDto.getTrainingTypeDto());
        Coach newCoach = coachMapper.toEntity(coachDto);
        coachRepository.save(newCoach);
        return "Email:"+newCoach.getUser().getEmail()+" Password: "+ coachDto.getRegisterDto().password();
    }

    @Override
    public HttpEntity<?> getCoachProfile(String email) {
        return null;
    }
}
