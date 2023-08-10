package hr.tvz.financije.repositories;

import hr.tvz.financije.repositories.entities.jooq.tables.records.ProfileRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static hr.tvz.financije.repositories.entities.jooq.Tables.PROFILE;

@Repository
@RequiredArgsConstructor
public class ProfileRepository {

    private final DSLContext dslContext;

    public Optional<ProfileRecord> findProfileByUsername(String username) {
        return Optional.ofNullable(dslContext.selectFrom(PROFILE)
                .where(PROFILE.USERNAME.eq(username))
                .fetchOne());
    }

    public ProfileRecord saveProfile(ProfileRecord record) {
        return dslContext.insertInto(PROFILE)
                .set(record)
                .onDuplicateKeyUpdate()
                .set(record)
                .returning()
                .fetchOne();
    }

    public void deleteProfileById(int id) {
        dslContext.deleteFrom(PROFILE)
                .where(PROFILE.ID.eq(id))
                .execute();
    }
}
