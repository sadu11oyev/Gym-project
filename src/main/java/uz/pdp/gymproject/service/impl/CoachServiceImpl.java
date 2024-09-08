package uz.pdp.gymproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.gymproject.dto.CoachDto;
import uz.pdp.gymproject.entity.Coach;
import uz.pdp.gymproject.entity.Role;
import uz.pdp.gymproject.entity.TrainingType;
import uz.pdp.gymproject.entity.User;
import uz.pdp.gymproject.entity.enums.RoleName;
import uz.pdp.gymproject.mappers.CoachMapper;
import uz.pdp.gymproject.mappers.TrainingTypeMapper;
import uz.pdp.gymproject.model.request.CoachUpdateReqDto;
import uz.pdp.gymproject.model.response.CoachResDto;
import uz.pdp.gymproject.model.response.dto.CoachResDto2;
import uz.pdp.gymproject.model.response.dto.TraineeRes;
import uz.pdp.gymproject.repo.*;
import uz.pdp.gymproject.service.AuthService;
import uz.pdp.gymproject.service.CoachService;
import uz.pdp.gymproject.service.TrainingTypeService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CoachServiceImpl implements CoachService {
    private final TrainingTypeMapper trainingTypeMapper;
    private final CoachRepository coachRepository;
    private final CoachMapper coachMapper;
    private final AuthService authService;
    private final TrainingTypeService trainingTypeService;
    private final UserRepository userRepository;
    private final TrainingTypeRepository trainingTypeRepository;
    private final TraineeCoachRepository traineeCoachRepository;
    private final RoleRepository roleRepository;

    @Override
    public String save(CoachDto coachDto) {
        String userName = authService.register(coachDto.getRegisterDto());
        User user = userRepository.findByUsername(userName);
        Role roleName = roleRepository.findByRoleName(RoleName.ROLE_COACH.name());
        user.setRoles(List.of(roleName));
        TrainingType trainingType = trainingTypeMapper.toEntity(coachDto.getTrainingTypeDto());
        trainingTypeRepository.save(trainingType);
        Coach newCoach = coachMapper.toEntity(coachDto);
        newCoach.setTrainingType(trainingType);
        newCoach.setUser(user);
        coachRepository.save(newCoach);
        return "Email:"+newCoach.getUser().getUsername()+" Password: "+ coachDto.getRegisterDto().password();
    }

    @Override
    public CoachResDto2 getCoachProfile(User user) {
        Coach coach = coachRepository.findByUserId(user.getId());
        String specialization = trainingTypeRepository.findById(coach.getTrainingType().getId()).get().getName();
        CoachResDto coachResDto = CoachResDto.builder()
                .userName(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .specializations(specialization)
                .build();
        List<UUID> traineeIdList = traineeCoachRepository.getTraineeIdByCoachId(coach.getId());
        List<TraineeRes> traineeResList = traineeIdList.stream().map(traineeId->{
            User trainee = userRepository.findByTraieeIdIAndActive(traineeId);
            return TraineeRes.builder()
                    .userName(trainee.getUsername())
                    .firstName(trainee.getFirstName())
                    .lastName(trainee.getLastName())
                    .build();
        }).toList();
        return CoachResDto2.builder()
                .coachResDto(coachResDto)
                .traineeRes(traineeResList)
                .build();
    }

    @Override
    public CoachResDto2 updateCoach(CoachUpdateReqDto coachUpdateReqDto) {
        User currentUser = userRepository.findByUsername(coachUpdateReqDto.getCoachResDto().getUserName());
        currentUser.setFirstName(coachUpdateReqDto.getCoachResDto().getFirstName());
        currentUser.setLastName(coachUpdateReqDto.getCoachResDto().getLastName());
        currentUser.setIsActive(coachUpdateReqDto.getIsActive());
        userRepository.save(currentUser);
        TrainingType trainingType = trainingTypeService.getTrainingTypeIdByName(coachUpdateReqDto.getCoachResDto().getSpecializations());
        Coach currentCoach = coachRepository.findByUserId(currentUser.getId());
        currentCoach.setTrainingType(trainingType);
        coachRepository.save(currentCoach);
        return getCoachProfile(currentUser);
    }

    @Override
    public CoachResDto generateCoachResDto(Coach coach) {
        return CoachResDto.builder()
                .userName(coach.getUser().getUsername())
                .firstName(coach.getUser().getFirstName())
                .lastName(coach.getUser().getLastName())
                .specializations(coachRepository.findSpecializations(coach.getId()))
                .build();
    }
}
