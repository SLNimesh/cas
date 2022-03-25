--CREATE DATABASE cas;

--CREATE SCHEMA auth;

CREATE TABLE auth.users(
    id BIGINT PRIMARY KEY,
	firstname VARCHAR(16),
	lastname VARCHAR(16),
	username VARCHAR(16) UNIQUE NOT NULL,
	email VARCHAR(64) UNIQUE NOT NULL,
	password VARCHAR,
	phone_number VARCHAR(16),
	sign_in VARCHAR(12) NOT NULL,
	profile_pic TEXT,
	banned_account BOOLEAN,
	created_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
	modified_date TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE auth.roles(
	id BIGINT PRIMARY KEY,
	role_name VARCHAR(16) UNIQUE NOT NULL,
	description TEXT
);

CREATE TABLE auth.user_roles(
    id BIGINT PRIMARY KEY,
	user_id BIGINT NOT NULL,
	role_id BIGINT NOT NULL,
	FOREIGN KEY (user_id) REFERENCES auth.users(id),
	FOREIGN KEY (role_id) REFERENCES auth.roles(id)
);

ALTER TABLE auth.users ADD COLUMN user_type VARCHAR(24) NOT NULL;