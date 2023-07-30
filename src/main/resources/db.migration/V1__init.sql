CREATE TABLE IF NOT EXISTS profile (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    hashed_password VARCHAR(255) NOT NULL,
    salt CHAR(36) NOT NULL
);

CREATE TABLE IF NOT EXISTS currency (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    code CHAR(3) NOT NULL,
    symbol VARCHAR(50) NOT NULL,
    exchange_rate INT NOT NULL,
    last_update_date DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    icon VARCHAR(50) NOT NULL,
    color CHAR(7) NOT NULL,
    profile_id INT REFERENCES profile
);

CREATE TABLE IF NOT EXISTS account (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    amount INT NOT NULL,
    type VARCHAR(10) NOT NULL,
    color CHAR(7) NOT NULL,
    source VARCHAR(10) NOT NULL,
    profile_id INT NOT NULL REFERENCES profile,
    currency_id INT NOT NULL REFERENCES currency
);

CREATE TABLE IF NOT EXISTS record (
    id SERIAL PRIMARY KEY,
    amount INT NOT NULL,
    type VARCHAR(10) NOT NULL,
    record_date DATE NOT NULL,
    description VARCHAR(255),
    account_id INT NOT NULL REFERENCES account,
    receiving_account_id INT REFERENCES account
);

CREATE INDEX IF NOT EXISTS record_type_record_date_idx ON record(type, record_date);