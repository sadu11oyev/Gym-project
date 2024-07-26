package uz.pdp.gymproject.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.gymproject.model.response.CoachResDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoachUpdateReqDto{
    private CoachResDto coachResDto;
    private Boolean isActive;

}
