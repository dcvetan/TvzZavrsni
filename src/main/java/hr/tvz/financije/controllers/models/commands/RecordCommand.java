package hr.tvz.financije.controllers.models.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record RecordCommand(
        @Positive Integer id,
        @NotNull @Positive Double amount,
        @NotBlank @Size(max = 10) String type,
        @NotNull LocalDateTime recordDate,
        @Size(max = 255) String description,
        @NotNull @Positive Integer categoryId,
        @NotNull @Positive Integer accountId,
        @Positive Integer receivingAccountId
) {
}
