package uz.pdp.gymproject.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import uz.pdp.gymproject.dto.CoachDto;
import uz.pdp.gymproject.entity.Coach;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CoachMapper {
    Coach toEntity(CoachDto coachDto);
}
