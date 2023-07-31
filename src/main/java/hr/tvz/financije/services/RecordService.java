package hr.tvz.financije.services;

import hr.tvz.financije.controllers.models.RecordCommand;
import hr.tvz.financije.repositories.RecordRepository;
import hr.tvz.financije.repositories.entities.jooq.tables.records.RecordRecord;
import hr.tvz.financije.services.models.Account;
import hr.tvz.financije.services.models.Record;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository repository;
    private final AccountService accountService;

    public List<Record> getRecords() {
        List<Account> profileAccounts = accountService.getAccounts();
        return repository.getRecords(profileAccounts.stream().map(Account::id).toList())
                .stream()
                .map(this::mapToRecord).toList();
    }

    public Record saveRecord(RecordCommand command) {
        return mapToRecord(repository.saveRecord(mapToRecordRecord(command)));
    }

    public void deleteRecordById(int id) {
        repository.deleteRecordById(id);
    }

    private Record mapToRecord(RecordRecord record) {
        return new Record(record.getId(),
                record.getAmount(),
                record.getType(),
                record.getRecordDate(),
                record.getDescription(),
                record.getCategoryId(),
                record.getAccountId(),
                record.getReceivingAccountId());
    }

    private RecordRecord mapToRecordRecord(RecordCommand command) {
        return new RecordRecord(command.id(),
                command.amount(),
                command.type(),
                command.recordDate(),
                command.description(),
                command.categoryId(),
                command.accountId(),
                command.receivingAccountId());
    }
}
