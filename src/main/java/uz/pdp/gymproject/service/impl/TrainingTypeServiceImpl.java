package uz.pdp.gymproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.gymproject.dto.TrainingTypeDto;
import uz.pdp.gymproject.entity.TrainingType;
import uz.pdp.gymproject.mappers.TrainingTypeMapper;
import uz.pdp.gymproject.repo.TrainingTypeRepository;
import uz.pdp.gymproject.service.TrainingTypeService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TrainingTypeServiceImpl implements TrainingTypeService {
    private final TrainingTypeRepository trainingTypeRepository;
    private final TrainingTypeMapper trainingTypeMapper;
    @Override
    public ResponseEntity<List<TrainingTypeDto>> getTrainingTypes() {
        List<TrainingType> trainingTypes = trainingTypeRepository.findAll();
        List<TrainingTypeDto> trainingTypeDtos =trainingTypes.stream().map(trainingTypeMapper::toDto).toList();
        return ResponseEntity.status(200).body(trainingTypeDtos);
    }

}
