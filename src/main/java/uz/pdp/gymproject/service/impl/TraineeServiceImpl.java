package uz.pdp.gymproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.gymproject.dto.TraineeDto;
import uz.pdp.gymproject.entity.Trainee;
import uz.pdp.gymproject.entity.User;
import uz.pdp.gymproject.mappers.TraineeMapper;
import uz.pdp.gymproject.repo.TraineeRepository;
import uz.pdp.gymproject.repo.UserRepository;
import uz.pdp.gymproject.service.TraineeService;

@Service
@RequiredArgsConstructor
public class TraineeServiceImpl implements TraineeService {
    private final TraineeMapper traineeMapper;
    private final TraineeRepository traineeRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<Trainee> saveTrainee(TraineeDto traineeDto) {
        Trainee entity = traineeMapper.toEntity(traineeDto);
        Trainee save =  traineeRepository.save(entity);
        return ResponseEntity.status(200).body(save);
    }

    @Override
    public TraineeDto getTraineeProfile(String email) {
        User user = userRepository.findByEmail(email);
        Trainee trainee = traineeRepository.findByUserId(user.getId());
        return traineeMapper.toDto(trainee);
    }
}
