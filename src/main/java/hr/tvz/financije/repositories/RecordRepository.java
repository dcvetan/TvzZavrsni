package hr.tvz.financije.repositories;

import hr.tvz.financije.repositories.entities.jooq.tables.records.RecordRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static hr.tvz.financije.repositories.entities.jooq.Tables.RECORD;

@Repository
@RequiredArgsConstructor
public class RecordRepository {

    private final DSLContext dslContext;

    public List<RecordRecord> getRecords(List<Integer> accountIds) {
        return dslContext.selectFrom(RECORD)
                .where(RECORD.ACCOUNT_ID.in(accountIds))
                .fetch();
    }

    public Optional<RecordRecord> getRecordById(int id) {
        return Optional.ofNullable(dslContext.selectFrom(RECORD)
                .where(RECORD.ID.eq(id))
                .fetchOne());
    }

    public RecordRecord saveRecord(RecordRecord record) {
        return dslContext.insertInto(RECORD)
                .set(record)
                .onDuplicateKeyUpdate()
                .set(record)
                .returning()
                .fetchOne();
    }

    public void deleteRecordById(int id) {
        dslContext.deleteFrom(RECORD)
                .where(RECORD.ID.eq(id))
                .execute();
    }
}
