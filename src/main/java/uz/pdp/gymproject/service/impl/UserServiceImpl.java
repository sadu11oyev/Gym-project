package uz.pdp.gymproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.gymproject.dto.TraineeDto;
import uz.pdp.gymproject.entity.Trainee;
import uz.pdp.gymproject.entity.User;
import uz.pdp.gymproject.mappers.TraineeMapper;
import uz.pdp.gymproject.mappers.UserMapper;
import uz.pdp.gymproject.repo.TraineeRepository;
import uz.pdp.gymproject.repo.UserRepository;
import uz.pdp.gymproject.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

}
