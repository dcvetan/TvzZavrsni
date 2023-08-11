package hr.tvz.financije.services.models;

public record AccountDto(
        int id,
        String name,
        double amount,
        String type,
        String color,
        String source,
        int currencyId
) {
}
