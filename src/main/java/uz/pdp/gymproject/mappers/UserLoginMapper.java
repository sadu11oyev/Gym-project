package uz.pdp.gymproject.mappers;

import org.mapstruct.*;
import uz.pdp.gymproject.dto.LoginDto;
import uz.pdp.gymproject.entity.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserLoginMapper {
    User toEntity(LoginDto loginDto);
}
