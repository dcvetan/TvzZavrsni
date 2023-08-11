package hr.tvz.financije.controllers.models.commands;

import jakarta.validation.constraints.*;

public record AccountCommand(
        @Positive Integer id,
        @NotBlank @Size(max = 50) String name,
        @NotNull @PositiveOrZero Double amount,
        @NotBlank @Size(max = 20) String type,
        @NotBlank @Size(max = 20) String color,
        @NotBlank @Size(max = 10) String source,
        @NotNull @Positive Integer currencyId
) {
}
