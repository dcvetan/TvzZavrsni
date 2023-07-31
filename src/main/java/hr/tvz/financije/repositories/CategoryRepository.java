package hr.tvz.financije.repositories;


import hr.tvz.financije.repositories.entities.jooq.tables.records.CategoryRecord;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static hr.tvz.financije.repositories.entities.jooq.Tables.CATEGORY;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {

    private final DSLContext dslContext;

    public List<CategoryRecord> getCategories(int profileId) {
        return dslContext.selectFrom(CATEGORY)
                .where(CATEGORY.PROFILE_ID.eq(profileId))
                .or(CATEGORY.PROFILE_ID.isNull())
                .fetch();
    }

    public CategoryRecord saveCategory(CategoryRecord record) {
        return dslContext.insertInto(CATEGORY)
                .set(record)
                .onDuplicateKeyUpdate()
                .set(record)
                .returning()
                .fetchOne();
    }

    public void deleteCategoryById(int id) {
        dslContext.deleteFrom(CATEGORY)
                .where(CATEGORY.ID.eq(id))
                .execute();
    }
}
