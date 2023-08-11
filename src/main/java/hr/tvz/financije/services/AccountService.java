package hr.tvz.financije.services;

import hr.tvz.financije.controllers.models.commands.AccountCommand;
import hr.tvz.financije.repositories.AccountRepository;
import hr.tvz.financije.repositories.entities.jooq.tables.records.AccountRecord;
import hr.tvz.financije.security.services.UserDetailsServiceImpl;
import hr.tvz.financije.services.models.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;
    private final UserDetailsServiceImpl userDetailsService;

    public List<AccountDto> getAccounts() {
        int currentProfileId = userDetailsService.getCurrentUserProfileId();
        return repository.getAccounts(currentProfileId).stream()
                .map(this::mapToAccountDto)
                .toList();
    }

    public AccountDto saveAccount(AccountCommand command) {
        int currentProfileId = userDetailsService.getCurrentUserProfileId();
        AccountRecord savedRecord = repository.saveAccount(mapToAccountRecord(command, currentProfileId));
        return mapToAccountDto(repository.findAccountById(savedRecord.getId()).orElseThrow());
    }

    public void deleteAccountById(int id) {
        repository.deleteAccountById(id);
    }

    private AccountDto mapToAccountDto(AccountRecord record) {
        return new AccountDto(record.getId(),
                record.getName(),
                BigDecimal.valueOf(record.getAmount())
                        .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP)
                        .setScale(2, RoundingMode.HALF_UP)
                        .doubleValue(),
                record.getType(),
                record.getColor(),
                record.getSource(),
                record.getCurrencyId());
    }

    private AccountRecord mapToAccountRecord(AccountCommand command, int profileId) {
        return new AccountRecord(command.id(),
                command.name(),
                BigDecimal.valueOf(command.amount())
                        .setScale(2, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(100))
                        .longValueExact(),
                command.type(),
                command.color(),
                command.source(),
                profileId,
                command.currencyId());
    }
}
