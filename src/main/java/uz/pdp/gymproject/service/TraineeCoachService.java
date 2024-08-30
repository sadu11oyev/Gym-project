package uz.pdp.gymproject.service;

import uz.pdp.gymproject.entity.Coach;
import uz.pdp.gymproject.entity.Trainee;

public interface TraineeCoachService {
    void saveTraineeCoach(Trainee trainee, Coach coach);
}
