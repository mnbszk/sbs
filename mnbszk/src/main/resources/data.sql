-- CREATE EXTENSION pgcrypto;

INSERT INTO roles (role_id, role_name) VALUES (1, 'USER');
INSERT INTO roles (role_id, role_name) VALUES (2, 'ADMIN');

INSERT INTO users (user_id, username, password, email) VALUES (1, 'mnbszk', crypt('secret', gen_salt('bf', 10)), 'mnbszk@gmail.com');
INSERT INTO users (user_id, username, password, email) VALUES (2, 'admin',  crypt('secret', gen_salt('bf', 10)), 'admin@mnbszk.jp');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);