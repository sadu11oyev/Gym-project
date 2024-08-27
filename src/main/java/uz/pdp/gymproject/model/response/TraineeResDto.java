package uz.pdp.gymproject.model.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link uz.pdp.gymproject.entity.Trainee}
 */
@Data
@Builder
public class TraineeResDto {
    private String firstName;
    private String lastName;
    private LocalDate dateBirth;
    private String address;
    private Boolean isActive;
    private List<CoachResDto> coachResDto;

}
