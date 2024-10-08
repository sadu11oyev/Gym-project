package uz.pdp.gymproject.mappers;

import org.mapstruct.*;
import uz.pdp.gymproject.dto.RegisterDto;
import uz.pdp.gymproject.entity.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserRegisterMapper {
    User toEntity(RegisterDto registerDto);

    RegisterDto toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(RegisterDto registerDto, @MappingTarget User user);
}
