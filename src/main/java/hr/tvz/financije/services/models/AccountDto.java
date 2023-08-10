package hr.tvz.financije.services.models;

public record AccountDto(
        int id,
        String name,
        int amount,
        String type,
        String color,
        String source,
        String currencySymbol
) {
}
