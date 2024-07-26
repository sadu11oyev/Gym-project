package uz.pdp.gymproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

/**
 * DTO for {@link uz.pdp.gymproject.entity.Trainee}
 */
@Data
@AllArgsConstructor
public class TraineeDto {

    RegisterDto registerDto;
    LocalDate birthDate;
    String address;

}
