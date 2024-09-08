package uz.pdp.gymproject.model.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TraineeReqDto {
    private String userName;
    private String firstName;
    private String lastName;
    private LocalDate localDate;
    private String address;
    private String number;
    private Boolean isActive;
}
