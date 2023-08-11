package hr.tvz.financije.controllers.models.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record AccountCommand(
        @Positive Integer id,
        @NotBlank @Size(max = 50) String name,
        @NotNull @Positive Long amount,
        @NotBlank @Size(max = 10) String type,
        @NotBlank @Size(max = 7) String color,
        @NotBlank @Size(max = 10) String source,
        @NotNull @Positive Integer currencyId
) {
}
