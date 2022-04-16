-- liquibase formatted sql

-- changeset aldemoralinator:3
CREATE TABLE public.account_role (
    user_id integer NOT NULL,
    role_id integer NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY(user_id, role_id)
);
ALTER TABLE account_role
    ADD CONSTRAINT account_role_role_id_fkey FOREIGN KEY (role_id) REFERENCES role(id);
ALTER TABLE account_role
    ADD CONSTRAINT account_role_user_id_fkey FOREIGN KEY (user_id) REFERENCES account(id);
