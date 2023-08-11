package hr.tvz.financije.services;

import hr.tvz.financije.controllers.models.commands.RecordCommand;
import hr.tvz.financije.repositories.RecordRepository;
import hr.tvz.financije.repositories.entities.jooq.tables.records.RecordRecord;
import hr.tvz.financije.services.models.AccountDto;
import hr.tvz.financije.services.models.RecordDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository repository;
    private final AccountService accountService;

    public List<RecordDto> getRecords() {
        List<AccountDto> profileAccountDtos = accountService.getAccounts();
        return repository.getRecords(profileAccountDtos.stream().map(AccountDto::id).toList())
                .stream()
                .map(this::mapToRecordDto).toList();
    }

    public RecordDto saveRecord(RecordCommand command) {
        return mapToRecordDto(repository.saveRecord(mapToRecordRecord(command)));
    }

    public void deleteRecordById(int id) {
        repository.deleteRecordById(id);
    }

    private RecordDto mapToRecordDto(RecordRecord record) {
        return new RecordDto(record.getId(),
                BigDecimal.valueOf(record.getAmount())
                        .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP)
                        .setScale(2, RoundingMode.HALF_UP)
                        .doubleValue(),
                record.getType(),
                record.getRecordDate(),
                record.getDescription(),
                record.getCategoryId(),
                record.getAccountId(),
                record.getReceivingAccountId());
    }

    private RecordRecord mapToRecordRecord(RecordCommand command) {
        return new RecordRecord(command.id(),
                BigDecimal.valueOf(command.amount())
                        .setScale(2, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(100))
                        .longValueExact(),
                command.type(),
                command.recordDate(),
                command.description(),
                command.categoryId(),
                command.accountId(),
                command.receivingAccountId());
    }
}
