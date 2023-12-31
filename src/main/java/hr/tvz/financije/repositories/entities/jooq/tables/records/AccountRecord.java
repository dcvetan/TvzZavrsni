/*
 * This file is generated by jOOQ.
 */
package hr.tvz.financije.repositories.entities.jooq.tables.records;


import hr.tvz.financije.repositories.entities.jooq.tables.Account;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AccountRecord extends UpdatableRecordImpl<AccountRecord> implements Record8<Integer, String, Long, String, String, String, Integer, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.account.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.account.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.account.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.account.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.account.amount</code>.
     */
    public void setAmount(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.account.amount</code>.
     */
    public Long getAmount() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>public.account.type</code>.
     */
    public void setType(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.account.type</code>.
     */
    public String getType() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.account.color</code>.
     */
    public void setColor(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.account.color</code>.
     */
    public String getColor() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.account.source</code>.
     */
    public void setSource(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.account.source</code>.
     */
    public String getSource() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.account.profile_id</code>.
     */
    public void setProfileId(Integer value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.account.profile_id</code>.
     */
    public Integer getProfileId() {
        return (Integer) get(6);
    }

    /**
     * Setter for <code>public.account.currency_id</code>.
     */
    public void setCurrencyId(Integer value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.account.currency_id</code>.
     */
    public Integer getCurrencyId() {
        return (Integer) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row8<Integer, String, Long, String, String, String, Integer, Integer> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    @Override
    public Row8<Integer, String, Long, String, String, String, Integer, Integer> valuesRow() {
        return (Row8) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Account.ACCOUNT.ID;
    }

    @Override
    public Field<String> field2() {
        return Account.ACCOUNT.NAME;
    }

    @Override
    public Field<Long> field3() {
        return Account.ACCOUNT.AMOUNT;
    }

    @Override
    public Field<String> field4() {
        return Account.ACCOUNT.TYPE;
    }

    @Override
    public Field<String> field5() {
        return Account.ACCOUNT.COLOR;
    }

    @Override
    public Field<String> field6() {
        return Account.ACCOUNT.SOURCE;
    }

    @Override
    public Field<Integer> field7() {
        return Account.ACCOUNT.PROFILE_ID;
    }

    @Override
    public Field<Integer> field8() {
        return Account.ACCOUNT.CURRENCY_ID;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public Long component3() {
        return getAmount();
    }

    @Override
    public String component4() {
        return getType();
    }

    @Override
    public String component5() {
        return getColor();
    }

    @Override
    public String component6() {
        return getSource();
    }

    @Override
    public Integer component7() {
        return getProfileId();
    }

    @Override
    public Integer component8() {
        return getCurrencyId();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public Long value3() {
        return getAmount();
    }

    @Override
    public String value4() {
        return getType();
    }

    @Override
    public String value5() {
        return getColor();
    }

    @Override
    public String value6() {
        return getSource();
    }

    @Override
    public Integer value7() {
        return getProfileId();
    }

    @Override
    public Integer value8() {
        return getCurrencyId();
    }

    @Override
    public AccountRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public AccountRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public AccountRecord value3(Long value) {
        setAmount(value);
        return this;
    }

    @Override
    public AccountRecord value4(String value) {
        setType(value);
        return this;
    }

    @Override
    public AccountRecord value5(String value) {
        setColor(value);
        return this;
    }

    @Override
    public AccountRecord value6(String value) {
        setSource(value);
        return this;
    }

    @Override
    public AccountRecord value7(Integer value) {
        setProfileId(value);
        return this;
    }

    @Override
    public AccountRecord value8(Integer value) {
        setCurrencyId(value);
        return this;
    }

    @Override
    public AccountRecord values(Integer value1, String value2, Long value3, String value4, String value5, String value6, Integer value7, Integer value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AccountRecord
     */
    public AccountRecord() {
        super(Account.ACCOUNT);
    }

    /**
     * Create a detached, initialised AccountRecord
     */
    public AccountRecord(Integer id, String name, Long amount, String type, String color, String source, Integer profileId, Integer currencyId) {
        super(Account.ACCOUNT);

        setId(id);
        setName(name);
        setAmount(amount);
        setType(type);
        setColor(color);
        setSource(source);
        setProfileId(profileId);
        setCurrencyId(currencyId);
        resetChangedOnNotNull();
    }
}
