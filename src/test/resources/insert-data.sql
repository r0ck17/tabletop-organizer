INSERT INTO tabletop_test.user_role (role) VALUES ('USER');
INSERT INTO tabletop_test.user_role (role) VALUES ('ADMIN');

INSERT INTO tabletop_test."user" (role_id, login, name, password) VALUES (2, '1', 'user1', '1');
INSERT INTO tabletop_test."user" (role_id, login, name, password) VALUES (1, '2', 'user2', '2');
INSERT INTO tabletop_test."user" (role_id, login, name, password) VALUES (1, '3', 'user3', '3');

INSERT INTO tabletop_test.boardgame (max_players, min_players, price, year, language, name, publisher) VALUES (5, 1, 100, 2010, 'Russian', 'game1', 'lavka');
INSERT INTO tabletop_test.boardgame (max_players, min_players, price, year, language, name, publisher) VALUES (8, 2, 200, 2012, 'Russian', 'game2', 'hobby games');
INSERT INTO tabletop_test.boardgame (max_players, min_players, price, year, language, name, publisher) VALUES (8, 1, 300, 2015, 'Russian', 'game3', 'gaga');

INSERT INTO tabletop_test.user_boardgame (price, purchase_date, boardgame_id, user_id) VALUES (400, null, 1, 2);
INSERT INTO tabletop_test.user_boardgame (price, purchase_date, boardgame_id, user_id) VALUES (300, null, 2, 2);
INSERT INTO tabletop_test.user_boardgame (price, purchase_date, boardgame_id, user_id) VALUES (200, null, 3, 1);
INSERT INTO tabletop_test.user_boardgame (price, purchase_date, boardgame_id, user_id) VALUES (500, null, 3, 3);