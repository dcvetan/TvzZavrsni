package hr.tvz.financije.services.models.responses;

import java.util.Map;

public record ExchangeRatesResponse(
        boolean success,
        long timestamp,
        String base,
        String date,
        Map<String, Double> rates
) {}
