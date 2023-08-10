package hr.tvz.financije.controllers.models.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProfileCommand(
        @Positive Integer id,
        @NotBlank @Size(max = 50) String username,
        @NotBlank @Size(max = 255) String password
) {
}
