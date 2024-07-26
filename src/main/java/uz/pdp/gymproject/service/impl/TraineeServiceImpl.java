package uz.pdp.gymproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.gymproject.dto.TraineeDto;
import uz.pdp.gymproject.entity.Coach;
import uz.pdp.gymproject.entity.Trainee;
import uz.pdp.gymproject.entity.User;
import uz.pdp.gymproject.mappers.TraineeMapper;
import uz.pdp.gymproject.model.request.TraineeReqDto;
import uz.pdp.gymproject.model.response.CoachResDto;
import uz.pdp.gymproject.model.response.TraineeResDto;
import uz.pdp.gymproject.model.response.TraineeUpdateResDto;
import uz.pdp.gymproject.repo.CoachRepository;
import uz.pdp.gymproject.repo.TraineeRepository;
import uz.pdp.gymproject.repo.UserRepository;
import uz.pdp.gymproject.service.AuthService;
import uz.pdp.gymproject.service.TraineeService;

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
    private final AuthService authService;

    @Override
    public String saveTrainee(TraineeDto traineeDto) {
        authService.register(traineeDto.getRegisterDto());
        Trainee entity = traineeMapper.toEntity(traineeDto);
        traineeRepository.save(entity);
        return "Email: "+traineeDto.getRegisterDto().email()+" Password: "+traineeDto.getRegisterDto().password();
    }

    @Override
    public TraineeResDto getTraineeProfile(String email) {
        Trainee trainee = traineeRepository.findByUserId(userRepository.findByEmail(email).getId());
        List<UUID> coachIdList = coachRepository.findAllIdByTraineeId(trainee.getId());
        List<CoachResDto> coachResDtos = coachIdList.stream().map(coachId -> {
            Optional<Coach> optionalCoach = coachRepository.findById(coachId);
            return optionalCoach.map(coach -> CoachResDto.builder()
                    .gmail(coach.getUser().getEmail())
                    .firstName(coach.getUser().getFirstName())
                    .lastName(coach.getUser().getLastName())
                    .specializations(coachRepository.findSpecializations(coachId))
                    .build()).orElse(null);
        }).toList();
        return TraineeResDto.builder()
                 .firstName(trainee.getUser().getFirstName())
                 .lastName(trainee.getUser().getFirstName())
                 .address(trainee.getAddress())
                 .dateBirth(trainee.getBirthDate())
                 .isActive(trainee.getUser().getIsActive())
                 .coachResDto(coachResDtos)
                 .build();
    }

    @Override
    public TraineeUpdateResDto getUpdateTraineeProfile(TraineeReqDto traineeReqDto) {
        User user = userRepository.findByEmail(traineeReqDto.getGmail());
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
                .traineeResDto(getTraineeProfile(user.getEmail()))
                .gmail(user.getEmail())
                .build();
    }

    @Override
    public String deleteTrainee(String email) {
        User user = userRepository.findByEmail(email);
        user.setIsActive(false);
        userRepository.save(user);
        return user.getEmail();
    }
}
