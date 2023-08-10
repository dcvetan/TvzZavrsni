package hr.tvz.financije.controllers;

import hr.tvz.financije.controllers.models.JWTToken;
import hr.tvz.financije.controllers.models.commands.ProfileCommand;
import hr.tvz.financije.security.services.AuthenticationService;
import hr.tvz.financije.security.jwt.JwtFilter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authenticate")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:9000")
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/signup")
    public ResponseEntity<JWTToken> signUp(@Valid @RequestBody ProfileCommand command) {
        JWTToken jwt = service.signUp(command);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt.token());

        return new ResponseEntity<>(jwt, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<JWTToken> signIn(@Valid @RequestBody ProfileCommand command) {
        JWTToken jwt = service.signIn(command);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt.token());

        return new ResponseEntity<>(jwt, httpHeaders, HttpStatus.OK);
    }
}
