package hr.tvz.financije.services.models;

public record Category(
        int id,
        String name,
        String icon,
        String color,
        boolean isDefault
) {
}
