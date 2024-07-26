package uz.pdp.gymproject.model.response.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.gymproject.model.response.CoachResDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CoachResDto2 {
    private CoachResDto coachResDto;
    private List<TraineeRes> traineeRes;

}
