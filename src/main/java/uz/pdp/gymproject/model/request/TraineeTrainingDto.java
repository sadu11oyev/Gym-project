package uz.pdp.gymproject.model.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TraineeTrainingDto {
    private String traineeEmail;
    private LocalDate from;
    private LocalDate to;
    private String coachEmail;
    private String specialization;

}
