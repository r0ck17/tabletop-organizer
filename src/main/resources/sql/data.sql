INSERT INTO tabletop.users (user_role, username, email, password) VALUES ('USER', 'user1', 'user1@mail.ru', '1');
INSERT INTO tabletop.users (user_role, username, email, password) VALUES ('USER', 'user2', 'user2@mail.ru', '1');
INSERT INTO tabletop.users (user_role, username, email, password) VALUES ('USER', 'user3', 'user3@mail.ru', '1');

INSERT INTO tabletop.boardgame (max_players, min_players, year, language, name, publisher) VALUES (5, 2, 2000, 'RU', 'Каркассон', 'Hobby World');
INSERT INTO tabletop.boardgame (max_players, min_players, year, language, name, publisher) VALUES (4, 2, 2008,  'RU', 'Пандемия', 'Стиль жизни');
INSERT INTO tabletop.boardgame (max_players, min_players, year, language, name, publisher) VALUES (4, 3, 1995, 'RU', 'Колонизаторы', 'Hobby World');
INSERT INTO tabletop.boardgame (max_players, min_players, year, language, name, publisher) VALUES (5, 2, 2005, 'RU', 'Билет на поезд: Европа', 'Мосигра');
INSERT INTO tabletop.boardgame (max_players, min_players, year, language, name, publisher) VALUES (5, 1, 2016,  'RU', 'Покорение Марса', 'Лавка игр');
INSERT INTO tabletop.boardgame (max_players, min_players, year, language, name, publisher) VALUES (5, 1, 2018,  'RU', 'Немезида', 'Hobby World');
INSERT INTO tabletop.boardgame (max_players, min_players, year, language, name, publisher) VALUES (5, 1, 2019,  'RU', 'Крылья', 'Лавка игр');
INSERT INTO tabletop.boardgame (max_players, min_players, year, language, name, publisher) VALUES (4, 2, 2014,  'RU', 'Орлеан', 'Gaga games');
INSERT INTO tabletop.boardgame (max_players, min_players, year, language, name, publisher) VALUES (6, 2, 2013,  'RU', 'Виноделие', 'Лавка игр');
INSERT INTO tabletop.boardgame (max_players, min_players, year, language, name, publisher) VALUES (12, 2, 2015,  'RU', 'Кодовые имена', 'Gaga games');
INSERT INTO tabletop.boardgame (max_players, min_players, year, language, name, publisher) VALUES (6, 3, 2001,  'RU', 'Манчкин', 'Hobby world');

INSERT INTO tabletop.user_boardgame (price, purchase_date, boardgame_id, user_id) VALUES (1200, '2022-03-12', 1, 1);
INSERT INTO tabletop.user_boardgame (price, purchase_date, boardgame_id, user_id) VALUES (900, '2022-04-21', 2, 1);
INSERT INTO tabletop.user_boardgame (price, purchase_date, boardgame_id, user_id) VALUES (800, '2021-08-03', 11, 1);
INSERT INTO tabletop.user_boardgame (price, purchase_date, boardgame_id, user_id) VALUES (2500, '2020-05-18', 5, 1);
INSERT INTO tabletop.user_boardgame (price, purchase_date, boardgame_id, user_id) VALUES (2100, '2022-07-25', 4, 1);
INSERT INTO tabletop.user_boardgame (price, purchase_date, boardgame_id, user_id) VALUES (700, '2022-05-17', 10, 2);
INSERT INTO tabletop.user_boardgame (price, purchase_date, boardgame_id, user_id) VALUES (4000, '2022-11-28', 9, 2);
INSERT INTO tabletop.user_boardgame (price, purchase_date, boardgame_id, user_id) VALUES (1800, '2021-02-23', 2, 2);
INSERT INTO tabletop.user_boardgame (price, purchase_date, boardgame_id, user_id) VALUES (2500, '2022-12-29', 7, 2);
INSERT INTO tabletop.user_boardgame (price, purchase_date, boardgame_id, user_id) VALUES (2200, '2022-06-08', 3, 3);
INSERT INTO tabletop.user_boardgame (price, purchase_date, boardgame_id, user_id) VALUES (9000, '2022-09-30', 6, 3);
INSERT INTO tabletop.user_boardgame (price, purchase_date, boardgame_id, user_id) VALUES (2900, '2023-05-07', 8, 3);
