package hr.tvz.financije.services.models;

import java.time.LocalDateTime;

public record CurrencyDto(
        Integer id,
        String name,
        String code,
        String symbol,
        double exchangeRate,
        LocalDateTime lastUpdateDate
) {
}
