package hr.tvz.financije.services.models;

import java.time.LocalDateTime;

public record Record(
        int id,
        int amount,
        String type,
        LocalDateTime recordDate,
        String description,
        int categoryId,
        int accountId,
        Integer receivingAccountId
) {
}
