package uz.pdp.gymproject.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TraineeUpdateResDto {
    private String userName;
    private TraineeResDto traineeResDto;
}
