package uz.pdp.gymproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.gymproject.dto.TraineeDto;
import uz.pdp.gymproject.entity.Trainee;
import uz.pdp.gymproject.mappers.TraineeMapper;
import uz.pdp.gymproject.mappers.UserMapper;
import uz.pdp.gymproject.repo.TraineeRepository;
import uz.pdp.gymproject.repo.UserRepository;
import uz.pdp.gymproject.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TraineeRepository traineeRepository;
    private final UserMapper userMapper;
    private final TraineeMapper traineeMapper;
    @Override
    public ResponseEntity<Trainee> saveTrainee(TraineeDto traineeDto) {
        Trainee entity = traineeMapper.toEntity(traineeDto);
        Trainee save =  traineeRepository.save(entity);
        return ResponseEntity.status(200).body(save);
    }
}
