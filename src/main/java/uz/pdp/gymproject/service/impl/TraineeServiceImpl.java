package uz.pdp.gymproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.gymproject.dto.TraineeDto;
import uz.pdp.gymproject.entity.*;
import uz.pdp.gymproject.entity.enums.RoleName;
import uz.pdp.gymproject.mappers.TraineeMapper;
import uz.pdp.gymproject.model.request.AddTrainingReqDto;
import uz.pdp.gymproject.model.request.TraineeReqDto;
import uz.pdp.gymproject.model.request.TraineeTrainingDto;
import uz.pdp.gymproject.model.request.UpdateCoachList;
import uz.pdp.gymproject.model.response.CoachResDto;
import uz.pdp.gymproject.model.response.TraineeResDto;
import uz.pdp.gymproject.model.response.TraineeTrainingResDto;
import uz.pdp.gymproject.model.response.TraineeUpdateResDto;
import uz.pdp.gymproject.repo.*;
import uz.pdp.gymproject.service.AuthService;
import uz.pdp.gymproject.service.CoachService;
import uz.pdp.gymproject.service.TraineeCoachService;
import uz.pdp.gymproject.service.TraineeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TraineeServiceImpl implements TraineeService {
    private final TraineeMapper traineeMapper;
    private final TraineeRepository traineeRepository;
    private final UserRepository userRepository;
    private final CoachRepository coachRepository;
    private final CoachService coachService;
    private final AuthService authService;
    private final TraineeCoachService traineeCoachService;
    private final TrainingRepository trainingRepository;
    private final RoleRepository roleRepository;
    private final TraineeCoachRepository traineeCoachRepository;
    private final TrainingTypeRepository trainingTypeRepository;

    @Override
    public String saveTrainee(TraineeDto traineeDto) {
        String userName = authService.register(traineeDto.getRegisterDto());
        User user = userRepository.findByUsername(userName);
        Role roleUser = roleRepository.findByRoleName(RoleName.ROLE_TRAINEE.name());
        user.setRoles(List.of(roleUser));
        Trainee entity = traineeMapper.toEntity(traineeDto);
        entity.setUser(user);
        traineeRepository.save(entity);
        return "Email: "+traineeDto.getRegisterDto().userName()+" Password: "+traineeDto.getRegisterDto().password();
    }

    @Override
    public TraineeResDto getTraineeProfile(User user) {
        Trainee trainee = traineeRepository.findByUserId(user.getId());
        List<UUID> coachIdList = coachRepository.findAllIdByTraineeId(trainee.getId());
        List<CoachResDto> coachResDtos = coachIdList.stream().map(coachId -> {
            Optional<Coach> optionalCoach = coachRepository.findById(coachId);
            return optionalCoach.map(coachService::generateCoachResDto).orElse(null);
        }).toList();
        return TraineeResDto.builder()
                 .firstName(trainee.getUser().getFirstName())
                 .lastName(trainee.getUser().getLastName())
                 .address(trainee.getAddress())
                 .dateBirth(trainee.getBirthDate())
                 .isActive(trainee.getUser().getIsActive())
                 .coachResDto(coachResDtos)
                 .build();
    }

    @Override
    public TraineeUpdateResDto getUpdateTraineeProfile(TraineeReqDto traineeReqDto) {
        User user = userRepository.findByUsername(traineeReqDto.getUserName());
        user.setFirstName(traineeReqDto.getFirstName());
        user.setLastName(traineeReqDto.getLastName());
        user.setPhone(traineeReqDto.getNumber());
        user.setIsActive(traineeReqDto.getIsActive());
        userRepository.save(user);
        Trainee trainee = traineeRepository.findByUserId(user.getId());
        trainee.setUser(user);
        trainee.setAddress(traineeReqDto.getAddress());
        trainee.setBirthDate(traineeReqDto.getLocalDate());
        traineeRepository.save(trainee);
        return TraineeUpdateResDto.builder()
                .traineeResDto(getTraineeProfile(user))
                .userName(user.getUsername())
                .build();
    }

    @Override
    public String deleteTrainee(String userName) {
        User user = userRepository.findByUsername(userName);
        user.setIsActive(false);
        userRepository.save(user);
        return user.getUsername();
    }

    @Override
    public List<CoachResDto> updateCoachList(UpdateCoachList updateCoachList) {
        Trainee trainee = traineeRepository.findByUserId(userRepository.findByUsername(updateCoachList.getUserName()).getId());
        List<CoachResDto> coachResDtos = new ArrayList<>();
        for (String coachUserName : updateCoachList.getCoachUserNameList()) {
            Coach coach = coachRepository.findByUserId(userRepository.findByUsername(coachUserName).getId());
            traineeCoachService.saveTraineeCoach(trainee,coach);
            coachResDtos.add(coachService.generateCoachResDto(coach));
        }
        return  coachResDtos;
    }

    @Override
    public TraineeTrainingResDto getTrainingList(TraineeTrainingDto traineeTrainingDto) {
        Coach coach = coachRepository.findByUserId(userRepository.findByUsername(traineeTrainingDto.getCoachUserName()).getId());
        TrainingType trainingType = traineeRepository.findByName(traineeTrainingDto.getSpecialization());
        Trainee trainee = traineeRepository.findByUserId(userRepository.findByUsername(traineeTrainingDto.getCoachUserName()).getId());
        Optional<Training> opt = trainingRepository.findByAllReferences(coach.getId(),trainingType.getId(),trainee.getId(),traineeTrainingDto.getFrom(),traineeTrainingDto.getTo());
        if (opt.isPresent()){
            Training training = opt.get();
            return new TraineeTrainingResDto(
                    training.getTrainingName(),
                    training.getDate(),
                    trainingType.getName(),
                    training.getDuration(),
                    coach.getUser().getUsername());
        }

        return null;
    }

    @Override
    public String addTraining(AddTrainingReqDto addTrainingReqDto) {
        Trainee trainee = traineeRepository.findByUserId(userRepository.findByUsername(addTrainingReqDto.getTraineeUserName()).getId());
        Coach coach = coachRepository.findByUserId(userRepository.findByUsername(addTrainingReqDto.getCoachUserName()).getId());
        TraineeCoach traineeCoach = TraineeCoach.builder()
                .coach(coach)
                .trainee(trainee)
                .build();
        traineeCoachRepository.save(traineeCoach);
        Training training = Training.builder()
                .trainee(trainee)
                .coach(coach)
                .trainingName(addTrainingReqDto.getTrainingName())
                .date(addTrainingReqDto.getData())
                .duration(addTrainingReqDto.getDuration())
                .trainingType(coach.getTrainingType())
                .build();
        trainingRepository.save(training);
        return "Saved!";
    }
}
