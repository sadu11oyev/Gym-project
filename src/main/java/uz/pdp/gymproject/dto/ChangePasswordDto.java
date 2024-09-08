package uz.pdp.gymproject.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChangePasswordDto {
    @NotBlank
    String userName;
    @NotBlank
    String password;
    @NotBlank
    String newPassword;
}
