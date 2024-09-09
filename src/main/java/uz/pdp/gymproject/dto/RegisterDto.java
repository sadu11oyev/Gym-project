package uz.pdp.gymproject.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link uz.pdp.gymproject.entity.User}
 */
public record RegisterDto(String firstName,
                          String lastName,
                          String phone,
                          @NotNull @NotEmpty String userName,
                          @NotNull @NotEmpty String password) implements Serializable {
}
