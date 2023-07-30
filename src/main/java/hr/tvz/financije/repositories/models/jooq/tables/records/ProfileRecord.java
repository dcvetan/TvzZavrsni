/*
 * This file is generated by jOOQ.
 */
package hr.tvz.financije.repositories.models.jooq.tables.records;


import hr.tvz.financije.repositories.models.jooq.tables.Profile;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ProfileRecord extends UpdatableRecordImpl<ProfileRecord> implements Record4<Integer, String, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.profile.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.profile.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.profile.username</code>.
     */
    public void setUsername(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.profile.username</code>.
     */
    public String getUsername() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.profile.hashed_password</code>.
     */
    public void setHashedPassword(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.profile.hashed_password</code>.
     */
    public String getHashedPassword() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.profile.salt</code>.
     */
    public void setSalt(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.profile.salt</code>.
     */
    public String getSalt() {
        return (String) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, String, String, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Integer, String, String, String> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Profile.PROFILE.ID;
    }

    @Override
    public Field<String> field2() {
        return Profile.PROFILE.USERNAME;
    }

    @Override
    public Field<String> field3() {
        return Profile.PROFILE.HASHED_PASSWORD;
    }

    @Override
    public Field<String> field4() {
        return Profile.PROFILE.SALT;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getUsername();
    }

    @Override
    public String component3() {
        return getHashedPassword();
    }

    @Override
    public String component4() {
        return getSalt();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getUsername();
    }

    @Override
    public String value3() {
        return getHashedPassword();
    }

    @Override
    public String value4() {
        return getSalt();
    }

    @Override
    public ProfileRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public ProfileRecord value2(String value) {
        setUsername(value);
        return this;
    }

    @Override
    public ProfileRecord value3(String value) {
        setHashedPassword(value);
        return this;
    }

    @Override
    public ProfileRecord value4(String value) {
        setSalt(value);
        return this;
    }

    @Override
    public ProfileRecord values(Integer value1, String value2, String value3, String value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ProfileRecord
     */
    public ProfileRecord() {
        super(Profile.PROFILE);
    }

    /**
     * Create a detached, initialised ProfileRecord
     */
    public ProfileRecord(Integer id, String username, String hashedPassword, String salt) {
        super(Profile.PROFILE);

        setId(id);
        setUsername(username);
        setHashedPassword(hashedPassword);
        setSalt(salt);
        resetChangedOnNotNull();
    }
}
