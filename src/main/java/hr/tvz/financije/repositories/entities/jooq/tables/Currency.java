/*
 * This file is generated by jOOQ.
 */
package hr.tvz.financije.repositories.entities.jooq.tables;


import hr.tvz.financije.repositories.entities.jooq.Keys;
import hr.tvz.financije.repositories.entities.jooq.Public;
import hr.tvz.financije.repositories.entities.jooq.tables.records.CurrencyRecord;

import java.time.LocalDateTime;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function6;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row6;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Currency extends TableImpl<CurrencyRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.currency</code>
     */
    public static final Currency CURRENCY = new Currency();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CurrencyRecord> getRecordType() {
        return CurrencyRecord.class;
    }

    /**
     * The column <code>public.currency.id</code>.
     */
    public final TableField<CurrencyRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.currency.name</code>.
     */
    public final TableField<CurrencyRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * The column <code>public.currency.code</code>.
     */
    public final TableField<CurrencyRecord, String> CODE = createField(DSL.name("code"), SQLDataType.CHAR(3).nullable(false), this, "");

    /**
     * The column <code>public.currency.symbol</code>.
     */
    public final TableField<CurrencyRecord, String> SYMBOL = createField(DSL.name("symbol"), SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * The column <code>public.currency.exchange_rate</code>.
     */
    public final TableField<CurrencyRecord, Integer> EXCHANGE_RATE = createField(DSL.name("exchange_rate"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.currency.last_update_date</code>.
     */
    public final TableField<CurrencyRecord, LocalDateTime> LAST_UPDATE_DATE = createField(DSL.name("last_update_date"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "");

    private Currency(Name alias, Table<CurrencyRecord> aliased) {
        this(alias, aliased, null);
    }

    private Currency(Name alias, Table<CurrencyRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.currency</code> table reference
     */
    public Currency(String alias) {
        this(DSL.name(alias), CURRENCY);
    }

    /**
     * Create an aliased <code>public.currency</code> table reference
     */
    public Currency(Name alias) {
        this(alias, CURRENCY);
    }

    /**
     * Create a <code>public.currency</code> table reference
     */
    public Currency() {
        this(DSL.name("currency"), null);
    }

    public <O extends Record> Currency(Table<O> child, ForeignKey<O, CurrencyRecord> key) {
        super(child, key, CURRENCY);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<CurrencyRecord, Integer> getIdentity() {
        return (Identity<CurrencyRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<CurrencyRecord> getPrimaryKey() {
        return Keys.CURRENCY_PKEY;
    }

    @Override
    public Currency as(String alias) {
        return new Currency(DSL.name(alias), this);
    }

    @Override
    public Currency as(Name alias) {
        return new Currency(alias, this);
    }

    @Override
    public Currency as(Table<?> alias) {
        return new Currency(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Currency rename(String name) {
        return new Currency(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Currency rename(Name name) {
        return new Currency(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Currency rename(Table<?> name) {
        return new Currency(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<Integer, String, String, String, Integer, LocalDateTime> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function6<? super Integer, ? super String, ? super String, ? super String, ? super Integer, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function6<? super Integer, ? super String, ? super String, ? super String, ? super Integer, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}