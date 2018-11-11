DROP TABLE IF EXISTS users CASCADE;
DROP SEQUENCE IF EXISTS users_user_id_seq;

CREATE SEQUENCE users_user_id_seq;

CREATE TABLE users (
    user_id INTEGER DEFAULT nextval('users_user_id_seq') PRIMARY KEY,
    username VARCHAR(32) NOT NULL,
    password VARCHAR(72) NOT NULL,
    email VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS roles CASCADE;
DROP SEQUENCE IF EXISTS roles_role_id_seq;

CREATE SEQUENCE roles_role_id_seq;

CREATE TABLE roles (
    role_id INTEGER DEFAULT nextval('roles_role_id_seq') PRIMARY KEY,
    role_name VARCHAR(32) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS users_roles;
ALTER TABLE IF EXISTS users_roles DROP CONSTRAINT users_roles_pkey;
ALTER TABLE IF EXISTS users_roles DROP CONSTRAINT users_roles_user_id_fkey;
ALTER TABLE IF EXISTS users_roles DROP CONSTRAINT users_roles_role_id_fkey;

CREATE TABLE users_roles (
    user_id INTEGER NOT NULL REFERENCES users(user_id),
    role_id INTEGER NOT NULL REFERENCES roles(role_id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT users_roles_pkey PRIMARY KEY(user_id, role_id)
);

DROP TABLE IF EXISTS todos;
DROP SEQUENCE IF EXISTS todos_todo_id_seq;

CREATE SEQUENCE todos_todo_id_seq;

CREATE TABLE todos (
    todo_id INTEGER DEFAULT nextval('todos_todo_id_seq') PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    details TEXT,
    finished BOOLEAN NOT NULL,
    created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);