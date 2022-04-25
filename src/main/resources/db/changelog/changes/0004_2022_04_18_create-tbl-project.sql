-- liquibase formatted sql

-- changeset aldemoralinator:4
CREATE TABLE project (
	id serial PRIMARY KEY,
	created_by integer NOT NULL,
	name VARCHAR ( 255 ) NOT NULL,
	github_link VARCHAR ( 255 ) NOT NULL,
	created_at TIMESTAMP NOT NULL DEFAULT NOW(),
	updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TRIGGER set_timestamp
BEFORE
UPDATE ON project
FOR EACH ROW
EXECUTE PROCEDURE trigger_set_timestamp();

ALTER TABLE project
    ADD CONSTRAINT project_created_by_fkey FOREIGN KEY (created_by) REFERENCES account(id);