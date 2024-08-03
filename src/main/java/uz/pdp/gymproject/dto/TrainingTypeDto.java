package uz.pdp.gymproject.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link uz.pdp.gymproject.entity.TrainingType}
 */

public record TrainingTypeDto(@NotNull @NotEmpty String name) implements Serializable {
}
