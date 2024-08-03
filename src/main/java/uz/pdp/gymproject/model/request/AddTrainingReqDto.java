package uz.pdp.gymproject.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTrainingReqDto {
    private String traineeUserName;
    private String coachUserName;
    private String trainingName;
    private LocalDate data;
    private Integer duration;

}
