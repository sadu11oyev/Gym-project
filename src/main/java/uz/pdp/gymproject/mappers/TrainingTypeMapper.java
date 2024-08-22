package uz.pdp.gymproject.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import uz.pdp.gymproject.dto.TrainingTypeDto;
import uz.pdp.gymproject.entity.TrainingType;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TrainingTypeMapper {
    TrainingType toEntity(TrainingTypeDto trainingTypeDto);

    TrainingTypeDto toDto(TrainingType trainingType);
}
