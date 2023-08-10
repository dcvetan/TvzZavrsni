package hr.tvz.financije.services.models;

public record CategoryDto(
        int id,
        String name,
        String icon,
        String color,
        boolean isDefault
) {
}
