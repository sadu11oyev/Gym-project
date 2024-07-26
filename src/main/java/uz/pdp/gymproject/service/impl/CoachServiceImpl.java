package uz.pdp.gymproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.gymproject.dto.CoachDto;
import uz.pdp.gymproject.entity.Coach;
import uz.pdp.gymproject.entity.User;
import uz.pdp.gymproject.mappers.CoachMapper;
import uz.pdp.gymproject.model.response.CoachResDto;
import uz.pdp.gymproject.model.response.dto.CoachResDto2;
import uz.pdp.gymproject.model.response.dto.TraineeRes;
import uz.pdp.gymproject.repo.*;
import uz.pdp.gymproject.service.AuthService;
import uz.pdp.gymproject.service.CoachService;
import uz.pdp.gymproject.service.TraineeService;
import uz.pdp.gymproject.service.TrainingTypeService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CoachServiceImpl implements CoachService {
    private final CoachRepository coachRepository;
    private final CoachMapper coachMapper;
    private final AuthService authService;
    private final TrainingTypeService trainingTypeService;
    private final UserRepository userRepository;
    private final TrainingTypeRepository trainingTypeRepository;
    private final TraineeRepository traineeRepository;
    private final TraineeService traineeService;
    private final TraineeCoachRepository traineeCoachRepository;

    @Override
    public String save(CoachDto coachDto) {
        authService.register(coachDto.getRegisterDto());
        trainingTypeService.save(coachDto.getTrainingTypeDto());
        Coach newCoach = coachMapper.toEntity(coachDto);
        coachRepository.save(newCoach);
        return "Email:"+newCoach.getUser().getEmail()+" Password: "+ coachDto.getRegisterDto().password();
    }

    @Override
    public CoachResDto2 getCoachProfile(String email) {
        User user = userRepository.findByEmail(email);
        Coach coach = coachRepository.findByUserId(user.getId());
        String specialization = trainingTypeRepository.findById(coach.getTrainingType().getId()).get().getName();
        CoachResDto coachResDto = CoachResDto.builder()
                .gmail(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .specializations(specialization)
                .build();
        List<UUID> traineeIdList = traineeCoachRepository.getTraineeIdByCoachId(coach.getId());
        List<TraineeRes> traineeResList = traineeIdList.stream().map(traineeId->{
            User trainee = userRepository.findByTraieeIdIAndActive(traineeId);
            return TraineeRes.builder()
                    .email(trainee.getEmail())
                    .firstName(trainee.getFirstName())
                    .lastName(trainee.getLastName())
                    .build();
        }).toList();
        return CoachResDto2.builder()
                .coachResDto(coachResDto)
                .traineeRes(traineeResList)
                .build();
    }
}
