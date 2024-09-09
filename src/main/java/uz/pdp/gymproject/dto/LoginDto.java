package uz.pdp.gymproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link uz.pdp.gymproject.entity.User}
 */
public record LoginDto(@NotNull @NotEmpty String userName,
                       @NotNull @NotBlank String password) implements Serializable {
}
