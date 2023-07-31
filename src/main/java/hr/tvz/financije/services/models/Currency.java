package hr.tvz.financije.services.models;

import java.time.LocalDateTime;

public record Currency(
        int id,
        String name,
        String code,
        String symbol,
        int exchangeRate,
        LocalDateTime lastUpdateDate
) {
}
