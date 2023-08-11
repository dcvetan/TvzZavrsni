package hr.tvz.financije.security.services;

import hr.tvz.financije.security.utils.SecurityUtils;
import hr.tvz.financije.services.ProfileService;
import hr.tvz.financije.services.models.ProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ProfileService profileService;

    public int getCurrentUserProfileId() throws NoSuchElementException {
        return profileService.findProfileByUsername(
                        SecurityUtils
                                .getCurrentUserUsername()
                                .orElseThrow()
                ).orElseThrow()
                .id();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) {
        return profileService
                .findProfileByUsername(username)
                .map(this::createSpringSecurityUser)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " was not found in the database"));
    }

    private User createSpringSecurityUser(ProfileDto profileDto) {
        return new User(
                profileDto.username(),
                profileDto.hashedPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
