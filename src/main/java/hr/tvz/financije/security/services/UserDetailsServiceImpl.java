package hr.tvz.financije.security.services;

import hr.tvz.financije.repositories.ProfileRepository;
import hr.tvz.financije.repositories.entities.jooq.tables.records.ProfileRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ProfileRepository profileRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) {
        return profileRepository
                .findProfileByUsername(username)
                .map(this::createSpringSecurityUser)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " was not found in the database"));
    }

    private User createSpringSecurityUser(ProfileRecord profileRecord) {
        return new User(
                profileRecord.getUsername(),
                profileRecord.getHashedPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
