/*
 * This file is generated by jOOQ.
 */
package hr.tvz.financije.repositories.entities.jooq;


import hr.tvz.financije.repositories.entities.jooq.tables.Account;
import hr.tvz.financije.repositories.entities.jooq.tables.Category;
import hr.tvz.financije.repositories.entities.jooq.tables.Record;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables in public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index ACCOUNT_CURRENCY_ID_IDX = Internal.createIndex(DSL.name("account_currency_id_idx"), Account.ACCOUNT, new OrderField[] { Account.ACCOUNT.CURRENCY_ID }, false);
    public static final Index CATEGORY_PROFILE_ID_IDX = Internal.createIndex(DSL.name("category_profile_id_idx"), Category.CATEGORY, new OrderField[] { Category.CATEGORY.PROFILE_ID }, false);
    public static final Index RECORD_ACCOUNT_ID_IDX = Internal.createIndex(DSL.name("record_account_id_idx"), Record.RECORD, new OrderField[] { Record.RECORD.ACCOUNT_ID }, false);
}
