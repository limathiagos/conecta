CREATE DATABASE backend;

USE backend;

SELECT * FROM postagem;

TRUNCATE TABLE user;

SELECT * FROM role;

INSERT INTO role(name) VALUES ('ROLE_ADMIN');
INSERT INTO role(name) VALUES ('ROLE_USER');

SELECT * FROM user_role;

drop database backend;
