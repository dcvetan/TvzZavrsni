package hr.tvz.financije.repositories.entities;

public record AccountEntity(
        int id,
        String name,
        int amount,
        String type,
        String color,
        String source,
        String currencySymbol
) {
}
