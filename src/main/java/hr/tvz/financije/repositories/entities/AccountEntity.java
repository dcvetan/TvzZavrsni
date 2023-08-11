package hr.tvz.financije.repositories.entities;

public record AccountEntity(
        int id,
        String name,
        long amount,
        String type,
        String color,
        String source,
        String currencySymbol
) {
}
