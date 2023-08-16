package hr.tvz.financije.services;

import hr.tvz.financije.controllers.models.commands.AccountCommand;
import hr.tvz.financije.repositories.AccountRepository;
import hr.tvz.financije.repositories.entities.jooq.tables.records.AccountRecord;
import hr.tvz.financije.security.services.UserDetailsServiceImpl;
import hr.tvz.financije.services.models.AccountDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private UserDetailsServiceImpl userDetailsService;

    @InjectMocks
    private AccountService accountService;

    private final List<AccountRecord> ACCOUNT_RECORDS = List.of(
            new AccountRecord(1, "name", 100L, "type", "color", "source", 1, 1),
            new AccountRecord(2, "name2", 200L, "type2", "color2", "source2", 2, 2)
    );

    private final List<AccountDto> ACCOUNT_DTOS = List.of(
            new AccountDto(1, "name", 1, "type", "color", "source", 1),
            new AccountDto(2, "name2", 2, "type2", "color2", "source2", 2)
    );

    private final AccountCommand ACCOUNT_COMMAND = new AccountCommand(
            1, "name", 1.0, "type", "color", "source", 1
    );

    private final int PROFILE_ID = 1;

    @Test
    void getAccounts() {
        when(accountRepository.getAccounts(PROFILE_ID)).thenReturn(ACCOUNT_RECORDS);
        when(userDetailsService.getCurrentUserProfileId()).thenReturn(PROFILE_ID);
        List<AccountDto> accountDtos = accountService.getAccounts();
        assertEquals(accountDtos, ACCOUNT_DTOS);
    }

    @Test
    void saveAccount() {
        when(accountRepository.saveAccount(ACCOUNT_RECORDS.get(0))).thenReturn(ACCOUNT_RECORDS.get(0));
        when(userDetailsService.getCurrentUserProfileId()).thenReturn(PROFILE_ID);
        AccountDto accountDto = accountService.saveAccount(ACCOUNT_COMMAND);
        assertEquals(accountDto, ACCOUNT_DTOS.get(0));
    }
}