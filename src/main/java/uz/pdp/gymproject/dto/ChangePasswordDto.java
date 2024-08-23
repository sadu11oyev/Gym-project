package uz.pdp.gymproject.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChangePasswordDto {
    @NotBlank
    String email;
    @NotBlank
    String password;
    @NotBlank
    String newPassword;
}
