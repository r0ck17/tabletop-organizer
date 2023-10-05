CREATE
DATABASE tabletop_organizer;

CREATE SCHEMA tabletop;

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
    login    varchar(128) NOT NULL UNIQUE,
    password varchar(128) NOT NULL,
    name     varchar(128) NOT NULL,
    role_id  int REFERENCES user_role (id)
);

-- Таблица "Издатели" настольных игр
CREATE TABLE publisher
(
    id   serial PRIMARY KEY,
    name varchar(128) NOT NULL
);

-- Таблица "Языки" настольных игр
CREATE TABLE language
(
    id   serial PRIMARY KEY,
    name varchar(32) NOT NULL
);

-- Таблица "Языки" настольных игр
CREATE TABLE genre
(
    id   serial PRIMARY KEY,
    name varchar(128) NOT NULL
);

-- Таблица "Настольные игры"
CREATE TABLE boardgame
(
    id           bigserial PRIMARY KEY,
    name         varchar(128) NOT NULL,
    price        int,
    year         smallint     NOT NULL,
    language_id  int REFERENCES language (id),
    publisher_id int REFERENCES publisher (id),
    min_players  smallint     NOT NULL,
);

-- Таблица "Жанры настольных игр"
CREATE TABLE boardgame_genre
(
    boardgame_id bigint REFERENCES boardgame (id),
    genre_id     int REFERENCES genre (id),
    UNIQUE (boardgame_id, genre_id)
);

-- Таблица "Настольные игры пользователей"
CREATE TABLE user_boardgame
(
    user_id      bigint REFERENCES tabletop.user (id),
    boardgame_id bigint REFERENCES boardgame (id),
    UNIQUE (user_id, boardgame_id)
);
