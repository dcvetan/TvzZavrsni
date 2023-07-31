package hr.tvz.financije.controllers;

import hr.tvz.financije.controllers.models.AccountCommand;
import hr.tvz.financije.services.AccountService;
import hr.tvz.financije.services.models.Account;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @GetMapping
    public ResponseEntity<List<Account>> getAccounts() {
        return ResponseEntity.ok(service.getAccounts());
    }

    @PostMapping
    public ResponseEntity<Account> saveAccount(@RequestBody @Valid AccountCommand command) {
        return ResponseEntity.ok(service.saveAccount(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccountById(@PathVariable int id) {
        service.deleteAccountById(id);
        return ResponseEntity.noContent().build();
    }
}
