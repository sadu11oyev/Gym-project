package uz.pdp.gymproject.model.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TraineeTrainingDto {
    private String traineeUserName;
    private LocalDate from;
    private LocalDate to;
    private String coachUserName;
    private String specialization;

}
