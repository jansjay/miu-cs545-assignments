INSERT INTO users (name) VALUES ('Joe Dones');

INSERT INTO post (title, content, user_id) VALUES ('Things Fall Apart', 'https://en.wikipedia.org/wiki/Things_Fall_Apart', 1);
INSERT INTO post (title, content, user_id) VALUES ('Fairy tales', 'https://en.wikipedia.org/wiki/Fairy_Tales_Told_for_Children._First_Collection', 1);
INSERT INTO post (title, content, user_id) VALUES ('The Divine Comedy', 'https://en.wikipedia.org/wiki/Divine_Comedy', 1);
INSERT INTO post (title, content, user_id) VALUES ('The Epic Of Gilgamesh', 'https://en.wikipedia.org/wiki/Epic_of_Gilgamesh', 1);
INSERT INTO post (title, content, user_id) VALUES ('Achaemenid Empire', 'https://en.wikipedia.org/wiki/Book_of_Job', 1);

INSERT INTO comment (name, post_id) VALUES ('Awesome!!!', 1);
INSERT INTO comment (name, post_id) VALUES ('What do you think!!!', 1);
INSERT INTO comment (name, post_id) VALUES ('This is not good!!!', 2);
INSERT INTO comment (name, post_id) VALUES ('Always!!!', 3);
