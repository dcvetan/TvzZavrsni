package hr.tvz.financije.repositories;

import hr.tvz.financije.repositories.entities.AccountEntity;
import hr.tvz.financije.repositories.entities.jooq.Keys;
import hr.tvz.financije.repositories.entities.jooq.tables.records.AccountRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static hr.tvz.financije.repositories.entities.jooq.Tables.ACCOUNT;
import static hr.tvz.financije.repositories.entities.jooq.Tables.CURRENCY;

@Repository
@RequiredArgsConstructor
public class AccountRepository {

    private final DSLContext dslContext;

    public List<AccountEntity> getAccounts(int profileId) {
        return dslContext.select(ACCOUNT.ID,
                        ACCOUNT.NAME,
                        ACCOUNT.AMOUNT,
                        ACCOUNT.TYPE,
                        ACCOUNT.COLOR,
                        ACCOUNT.SOURCE,
                        CURRENCY.SYMBOL)
                .from(ACCOUNT)
                .join(CURRENCY).onKey(Keys.ACCOUNT__ACCOUNT_CURRENCY_ID_FKEY)
                .where(ACCOUNT.PROFILE_ID.eq(profileId))
                .fetchInto(AccountEntity.class);
    }

    public AccountEntity getAccountById(int id) {
        return dslContext.select(ACCOUNT.ID,
                        ACCOUNT.NAME,
                        ACCOUNT.AMOUNT,
                        ACCOUNT.TYPE,
                        ACCOUNT.COLOR,
                        ACCOUNT.SOURCE,
                        CURRENCY.SYMBOL)
                .from(ACCOUNT)
                .join(CURRENCY).onKey(Keys.ACCOUNT__ACCOUNT_CURRENCY_ID_FKEY)
                .where(ACCOUNT.ID.eq(id))
                .fetchOneInto(AccountEntity.class);
    }

    public AccountRecord saveAccount(AccountRecord record) {
        return dslContext.insertInto(ACCOUNT)
                .set(record)
                .onDuplicateKeyUpdate()
                .set(record)
                .returning()
                .fetchOne();
    }

    public void deleteAccountById(int id) {
        dslContext.deleteFrom(ACCOUNT)
                .where(ACCOUNT.ID.eq(id))
                .execute();
    }
}
