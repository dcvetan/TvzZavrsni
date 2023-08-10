package hr.tvz.financije.controllers.models.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CategoryCommand(
        @Positive Integer id,

        @NotBlank @Size(max = 50) String name,
        @NotBlank @Size(max = 50) String icon,
        @NotBlank @Size(max = 7) String color
) {
}
