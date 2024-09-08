package uz.pdp.gymproject.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCoachList {
    private String userName;
    private List<String> coachUserNameList;
}
