package uz.pdp.gymproject.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CoachResDto {
    private String gmail;
    private String firstName;
    private String lastName;
    private String specializations;
}
