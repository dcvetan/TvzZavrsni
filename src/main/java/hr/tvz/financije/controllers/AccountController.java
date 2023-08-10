package hr.tvz.financije.controllers;

import hr.tvz.financije.controllers.models.commands.AccountCommand;
import hr.tvz.financije.services.AccountService;
import hr.tvz.financije.services.models.AccountDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:9000")
public class AccountController {

    private final AccountService service;

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAccounts() {
        return ResponseEntity.ok(service.getAccounts());
    }

    @PostMapping
    public ResponseEntity<AccountDto> saveAccount(@RequestBody @Valid AccountCommand command) {
        return ResponseEntity.ok(service.saveAccount(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccountById(@PathVariable int id) {
        service.deleteAccountById(id);
        return ResponseEntity.noContent().build();
    }
}
