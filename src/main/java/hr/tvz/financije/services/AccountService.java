package hr.tvz.financije.services;

import hr.tvz.financije.controllers.models.AccountCommand;
import hr.tvz.financije.repositories.AccountRepository;
import hr.tvz.financije.repositories.entities.AccountEntity;
import hr.tvz.financije.repositories.entities.jooq.tables.records.AccountRecord;
import hr.tvz.financije.services.models.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;

    public List<Account> getAccounts() {
        // todo real profileId
        return repository.getAccounts(1).stream().map(this::mapToAccount).toList();
    }

    public Account saveAccount(AccountCommand command) {
        // todo real profileId
        AccountRecord savedRecord = repository.saveAccount(mapToAccountRecord(command, 1));
        return mapToAccount(repository.getAccountById(savedRecord.getId()));
    }

    public void deleteAccountById(int id) {
        repository.deleteAccountById(id);
    }

    private Account mapToAccount(AccountEntity entity) {
        return new Account(entity.id(),
                entity.name(),
                entity.amount(),
                entity.type(),
                entity.color(),
                entity.source(),
                entity.currencySymbol());
    }

    private AccountRecord mapToAccountRecord(AccountCommand command, int profileId) {
        return new AccountRecord(command.id(),
                command.name(),
                command.amount(),
                command.type(),
                command.color(),
                command.source(),
                profileId,
                command.currencyId());
    }
}
