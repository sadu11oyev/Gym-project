package uz.pdp.gymproject.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraineeTrainingResDto {
    private String trainingName;
    private LocalDate trainingDate;
    private String trainingType;
    private Integer duration;
    private String coachName;

}
