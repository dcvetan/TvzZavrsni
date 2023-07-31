package hr.tvz.financije.services.models;

public record Account(
        int id,
        String name,
        int amount,
        String type,
        String color,
        String source,
        String currencySymbol
) {
}
