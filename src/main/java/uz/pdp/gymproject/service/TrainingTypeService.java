package uz.pdp.gymproject.service;

import org.springframework.http.ResponseEntity;
import uz.pdp.gymproject.dto.TrainingTypeDto;

import java.util.List;

public interface TrainingTypeService {
    ResponseEntity<List<TrainingTypeDto>> getTrainingTypes();

    String save(TrainingTypeDto trainingTypeDto);
}
