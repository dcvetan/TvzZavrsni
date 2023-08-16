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

    private static final String RECORD_INCOME = "Income";

    private final RecordRepository repository;
    private final AccountService accountService;

    public List<RecordDto> getRecords() {
        List<AccountDto> profileAccountDtos = accountService.getAccounts();
        return repository.getRecords(profileAccountDtos.stream().map(AccountDto::id).toList())
                .stream()
                .map(this::mapToRecordDto).toList();
    }

    public RecordDto saveRecord(RecordCommand command) {
        if (command.id() == null) {
            accountService.updateAccountAmount(
                    command.accountId(),
                    BigDecimal.valueOf(command.amount())
                            .setScale(3, RoundingMode.HALF_UP)
                            .multiply(BigDecimal.valueOf(command.type().compareTo(RECORD_INCOME) == 0 ? 100 : -100))
                            .longValueExact());
        } else {
            RecordRecord recordRecord = repository.getRecordById(command.id()).orElseThrow();

            BigDecimal previousAmount = BigDecimal.valueOf(recordRecord.getAmount())
                    .setScale(3, RoundingMode.HALF_UP)
                    .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);

            accountService.updateAccountAmount(
                    command.accountId(),
                    BigDecimal.valueOf(command.amount())
                            .setScale(3, RoundingMode.HALF_UP)
                            .subtract(previousAmount)
                            .multiply(BigDecimal.valueOf(command.type().compareTo(RECORD_INCOME) == 0 ? 100 : -100))
                            .longValueExact());
        }

        return mapToRecordDto(repository.saveRecord(mapToRecordRecord(command)));
    }

    public void deleteRecordById(int id) {
        RecordRecord recordRecord = repository.getRecordById(id).orElseThrow();
        accountService.updateAccountAmount(
                recordRecord.getAccountId(),
                recordRecord.getType()
                        .compareTo("Income") == 0 ? recordRecord.getAmount() : (recordRecord.getAmount() * -1)
        );
        repository.deleteRecordById(id);
    }

    private RecordDto mapToRecordDto(RecordRecord record) {
        return new RecordDto(record.getId(),
                BigDecimal.valueOf(record.getAmount())
                        .setScale(3, RoundingMode.HALF_UP)
                        .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP)
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
                        .setScale(3, RoundingMode.HALF_UP)
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
