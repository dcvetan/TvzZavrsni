package hr.tvz.financije.repositories;

import hr.tvz.financije.repositories.entities.jooq.tables.records.CurrencyRecord;
import hr.tvz.financije.services.models.CurrencyUpdateEntity;
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

    public void saveCurrencies(List<CurrencyUpdateEntity> entities) {
        dslContext.batched(configuration -> {
            for (CurrencyUpdateEntity entity : entities) {
                configuration.dsl().update(CURRENCY)
                        .set(CURRENCY.EXCHANGE_RATE, entity.exchangeRate())
                        .set(CURRENCY.LAST_UPDATE_DATE, entity.lastUpdateDate())
                        .where(CURRENCY.CODE.eq(entity.code()))
                        .execute();
            }
        });
    }
}
