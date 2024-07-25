package uz.pdp.gymproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO for {@link uz.pdp.gymproject.entity.Coach}
 */
@Data
@AllArgsConstructor
public class CoachDto {
    private RegisterDto registerDto;
    private TrainingTypeDto trainingTypeDto;

}
