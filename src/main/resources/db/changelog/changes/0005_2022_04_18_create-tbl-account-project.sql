-- liquibase formatted sql

-- changeset aldemoralinator:5
CREATE TABLE public.account_project (
    user_id integer NOT NULL,
    project_id integer NOT NULL,
    is_subscribed BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
	updated_at TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY(user_id, project_id)
);
ALTER TABLE account_project
    ADD CONSTRAINT account_project_project_id_fkey FOREIGN KEY (project_id) REFERENCES project(id);
ALTER TABLE account_project
    ADD CONSTRAINT account_project_user_id_fkey FOREIGN KEY (user_id) REFERENCES account(id);

CREATE TRIGGER set_timestamp
BEFORE
UPDATE ON account_project
FOR EACH ROW
EXECUTE PROCEDURE trigger_set_timestamp();
