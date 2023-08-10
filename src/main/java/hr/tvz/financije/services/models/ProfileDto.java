package hr.tvz.financije.services.models;

public record ProfileDto(
        int id,
        String username,
        String hashedPassword,
        String salt
) {
}
