package hr.tvz.financije.services;

import hr.tvz.financije.controllers.models.commands.RecordCommand;
import hr.tvz.financije.repositories.RecordRepository;
import hr.tvz.financije.repositories.entities.jooq.tables.records.RecordRecord;
import hr.tvz.financije.services.models.AccountDto;
import hr.tvz.financije.services.models.RecordDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecordServiceTest {

    @Mock
    private RecordRepository recordRepository;
    @Mock
    private AccountService accountService;

    @InjectMocks
    private RecordService recordService;

    private final List<AccountDto> ACCOUNT_DTOS = List.of(
            new AccountDto(1, "name", 1, "type", "color", "source", 1),
            new AccountDto(2, "name2", 2, "type2", "color2", "source2", 2)
    );

    private final List<RecordRecord> RECORD_RECORDS = List.of(
            new RecordRecord(1, 100L, "type", LocalDateTime.of(2023, 12, 12, 1, 1), null, 1, 1, null),
            new RecordRecord(2, 200L, "type2", LocalDateTime.of(2023, 12, 12, 1, 1), null, 2, 2, null)
    );

    private final List<RecordDto> RECORD_DTOS = List.of(
            new RecordDto(1, 1, "type", LocalDateTime.of(2023, 12, 12, 1, 1), null, 1, 1, null),
            new RecordDto(2, 2, "type2", LocalDateTime.of(2023, 12, 12, 1, 1), null, 2, 2, null)
    );

    @Test
    void getRecords() {
        when(accountService.getAccounts()).thenReturn(ACCOUNT_DTOS);
        when(recordRepository.getRecords(List.of(ACCOUNT_DTOS.get(0).id(), ACCOUNT_DTOS.get(1).id()))).thenReturn(RECORD_RECORDS);
        List<RecordDto> recordDtos = recordService.getRecords();
        assertEquals(recordDtos, RECORD_DTOS);
    }

    @Test
    void testSaveRecordWithNullId() {
        RecordCommand command = new RecordCommand(
                null, 1.0, "Income", LocalDateTime.of(2023, 12, 12, 1, 1), null, 1, 1, null
        );

        when(recordRepository.saveRecord(any())).thenReturn(RECORD_RECORDS.get(0));
        RecordDto recordDto = recordService.saveRecord(command);
        assertEquals(recordDto, RECORD_DTOS.get(0));
        verify(accountService).updateAccountAmount(1, 100L);
    }

    @Test
    void testSaveRecordWithExistingId() {
        RecordCommand command = new RecordCommand(
                1, 1.0, "Income", LocalDateTime.of(2023, 12, 12, 1, 1), null, 1, 1, null
        );

        when(recordRepository.getRecordById(1)).thenReturn(Optional.ofNullable(RECORD_RECORDS.get(1)));
        when(recordRepository.saveRecord(any())).thenReturn(RECORD_RECORDS.get(0));
        RecordDto recordDto = recordService.saveRecord(command);
        assertEquals(recordDto, RECORD_DTOS.get(0));
        verify(accountService).updateAccountAmount(1, -100L);
    }

    @Test
    void deleteRecord() {
        when(recordRepository.getRecordById(1)).thenReturn(Optional.ofNullable(RECORD_RECORDS.get(0)));
        recordService.deleteRecordById(1);
        verify(accountService).updateAccountAmount(1, -100L);
    }
}