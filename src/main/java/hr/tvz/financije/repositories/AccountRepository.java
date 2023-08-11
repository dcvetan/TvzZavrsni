package hr.tvz.financije.repositories;

import hr.tvz.financije.repositories.entities.jooq.tables.records.AccountRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static hr.tvz.financije.repositories.entities.jooq.Tables.ACCOUNT;

@Repository
@RequiredArgsConstructor
public class AccountRepository {

    private final DSLContext dslContext;

    public List<AccountRecord> getAccounts(int profileId) {
        return dslContext.selectFrom(ACCOUNT)
                .where(ACCOUNT.PROFILE_ID.eq(profileId))
                .fetch();
    }

    public Optional<AccountRecord> findAccountById(int id) {
        return Optional.ofNullable(dslContext.selectFrom(ACCOUNT)
                .where(ACCOUNT.ID.eq(id))
                .fetchOne());
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
