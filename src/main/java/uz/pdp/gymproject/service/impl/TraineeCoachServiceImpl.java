package uz.pdp.gymproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.gymproject.entity.Coach;
import uz.pdp.gymproject.entity.Trainee;
import uz.pdp.gymproject.entity.TraineeCoach;
import uz.pdp.gymproject.repo.TraineeCoachRepository;
import uz.pdp.gymproject.service.TraineeCoachService;

@Service
@RequiredArgsConstructor
public class TraineeCoachServiceImpl implements TraineeCoachService {

    private final TraineeCoachRepository traineeCoachRepository;

    @Override
    public void saveTraineeCoach(Trainee trainee, Coach coach) {
        TraineeCoach traineeCoach = TraineeCoach.builder()
                .trainee(trainee)
                .coach(coach)
                .build();
        traineeCoachRepository.save(traineeCoach);
    }
}
