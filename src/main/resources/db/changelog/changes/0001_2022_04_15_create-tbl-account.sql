-- liquibase formatted sql

-- changeset aldemoralinator:1
CREATE TABLE account (
	id serial PRIMARY KEY,
	username VARCHAR ( 50 ) UNIQUE NOT NULL,
	email VARCHAR ( 255 ) UNIQUE NOT NULL,
	password VARCHAR ( 255 ) NULL,
	is_account_non_expired BOOLEAN NOT NULL,
	is_account_non_locked BOOLEAN NOT NULL,
    is_credentials_non_expired BOOLEAN NOT NULL,
    is_enabled BOOLEAN NOT NULL,
	created_at TIMESTAMP NOT NULL DEFAULT NOW(),
	updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE FUNCTION trigger_set_timestamp()
RETURNS TRIGGER AS '
BEGIN
  NEW.updated_at = NOW();
RETURN NEW;
END;
' LANGUAGE plpgsql;

CREATE TRIGGER set_timestamp
BEFORE
UPDATE ON account
FOR EACH ROW
EXECUTE PROCEDURE trigger_set_timestamp();

