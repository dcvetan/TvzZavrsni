package hr.tvz.financije.repositories;

import hr.tvz.financije.repositories.entities.jooq.tables.records.CurrencyRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static hr.tvz.financije.repositories.entities.jooq.Tables.CURRENCY;

@Repository
@RequiredArgsConstructor
public class CurrencyRepository {

    private final DSLContext dslContext;

    public List<CurrencyRecord> getCurrencies() {
        return dslContext.selectFrom(CURRENCY).fetch();
    }

    public CurrencyRecord saveCurrency(CurrencyRecord record) {
        return dslContext.insertInto(CURRENCY)
                .set(record)
                .onDuplicateKeyUpdate()
                .set(record)
                .returning()
                .fetchOne();
    }
}
