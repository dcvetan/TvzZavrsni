package hr.tvz.financije.services;

import hr.tvz.financije.repositories.ProfileRepository;
import hr.tvz.financije.repositories.entities.jooq.tables.records.ProfileRecord;
import hr.tvz.financije.services.models.ProfileDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileServiceTest {

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private ProfileService profileService;

    private final String PROFILE_USERNAME = "user";

    private final ProfileRecord PROFILE_RECORD = new ProfileRecord(
            1, "user", "hash", "salt"
    );

    private final ProfileDto PROFILE_DTO = new ProfileDto(
            1, "user", "hash", "salt"
    );

    @Test
    void findProfileByUsername() {
        when(profileRepository.findProfileByUsername(PROFILE_USERNAME)).thenReturn(Optional.of(PROFILE_RECORD));
        Optional<ProfileDto> profileDto = profileService.findProfileByUsername(PROFILE_USERNAME);
        assertEquals(profileDto.get(), PROFILE_DTO);
    }
}