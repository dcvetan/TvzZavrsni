package hr.tvz.financije.services;

import hr.tvz.financije.repositories.ProfileRepository;
import hr.tvz.financije.repositories.entities.jooq.tables.records.ProfileRecord;
import hr.tvz.financije.services.models.ProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository repository;

    public Optional<ProfileDto> findByUsername(String username) {
        return repository.findProfileByUsername(username).map(this::mapToProfileDto);
    }

    private ProfileDto mapToProfileDto(ProfileRecord record) {
        return new ProfileDto(record.getId(),
                record.getUsername(),
                record.getHashedPassword(),
                record.getSalt());
    }
}
