package hr.tvz.financije.services.models;

import java.time.LocalDateTime;

public record CurrencyUpdateEntity(
        String code,
        long exchangeRate,
        LocalDateTime lastUpdateDate
) {
}
