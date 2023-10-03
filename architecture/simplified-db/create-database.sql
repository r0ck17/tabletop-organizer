CREATE DATABASE tabletop_organizer;

CREATE SCHEMA tabletop_simple;

-- Таблица "Роли пользователей"
CREATE TABLE user_role
(
    id   serial PRIMARY KEY,
    role varchar(32) NOT NULL
);

-- Таблица "Пользователи"
CREATE TABLE users
(
    id       bigserial PRIMARY KEY,
    login    varchar(128) NOT NULL,
    password varchar(128) NOT NULL,
    name     varchar(128) NOT NULL,
    role_id  int REFERENCES user_role (id)
);

-- Таблица "Настольные игры"
CREATE TABLE boardgame
(
    id          bigserial PRIMARY KEY,
    name        varchar(128) NOT NULL,
    price       int,
    year        smallint     NOT NULL,
    language    varchar(128) NOT NULL,
    publisher   varchar(128) NOT NULL,
    min_players smallint     NOT NULL,
    max_players smallint     NOT NULL
);

-- Таблица m2m "Настольные игры пользователей"
CREATE TABLE user_boardgame
(
    user_id      bigint REFERENCES users (id),
    boardgame_id bigint REFERENCES boardgame (id),
    UNIQUE (user_id, boardgame_id)
);
