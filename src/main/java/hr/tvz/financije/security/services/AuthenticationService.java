package hr.tvz.financije.security.services;

import hr.tvz.financije.controllers.models.JWTToken;
import hr.tvz.financije.controllers.models.commands.ProfileCommand;
import hr.tvz.financije.repositories.ProfileRepository;
import hr.tvz.financije.repositories.entities.jooq.tables.records.ProfileRecord;
import hr.tvz.financije.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public JWTToken signUp(ProfileCommand command) {
        UUID salt = UUID.randomUUID();
        String hashedPassword = passwordEncoder.encode(command.password() + salt);
        ProfileRecord profileRecord = new ProfileRecord(
                command.id(),
                command.username(),
                hashedPassword,
                salt.toString()
        );

        profileRepository.saveProfile(profileRecord);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(
                new UsernamePasswordAuthenticationToken(
                        command.username(),
                        command.password() + salt
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new JWTToken(tokenProvider.createToken(authentication));
    }

    public JWTToken signIn(ProfileCommand command) {
        ProfileRecord profileRecord = profileRepository.findProfileByUsername(command.username()).orElseThrow();

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(
                new UsernamePasswordAuthenticationToken(
                        command.username(),
                        command.password() + profileRecord.getSalt()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new JWTToken(tokenProvider.createToken(authentication));
    }
}