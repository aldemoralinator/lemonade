-- liquibase formatted sql

-- changeset aldemoralinator:2
CREATE TABLE role (
	id serial PRIMARY KEY,
	name VARCHAR ( 50 ) UNIQUE NOT NULL
);
