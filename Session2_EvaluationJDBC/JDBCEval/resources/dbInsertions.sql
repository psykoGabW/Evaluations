/*
Insertion of some data
WARNING : This script delete all data of database tables

*/

SET search_path TO appstore;


TRUNCATE TABLE  tb_customer CASCADE;

INSERT INTO TB_customer (firstname, lastname, email, knickname, birthdate) VALUES ('Martin', 'Gore', 'martin.gore@gmailfoo.com', 'Martin@DM', '1961-07-23');
INSERT INTO TB_customer (firstname, lastname, email, knickname, birthdate) VALUES ('Dave', 'Gahan', 'dave.gahan@gmailfoo.com', 'Dave@DM', '1962-05-09');
INSERT INTO TB_customer (firstname, lastname, email, knickname, birthdate) VALUES ('Andrew', 'Fletcher', 'andrew.fletchern@gmailfoo.com', 'Andrew@DM', '1961-07-08');
INSERT INTO TB_customer (firstname, lastname, email, knickname, birthdate) VALUES ('Alan', 'Wilder', 'alan.wilder@gmailfoo.com', 'Alan@DM', '1959-06-01');

/*
pk_id Serial PRIMARY KEY,
publisher VARCHAR(25),
name  VARCHAR(50) NOT NULL,
credits_price  DECIMAL(5,2) NOT NULL,
PEGI INTEGER,
short_description VARCHAR(256),
full_description TEXT,
-- fk_publisher_id INTEGER REFERENCES APPStore.TB_Publisher(pk_id),
website_url   VARCHAR(512)
*/

TRUNCATE TABLE TB_videogame CASCADE;

INSERT INTO TB_videogame(publisher, name, credits_price, PEGI, short_description) VALUES ('Namco', 'Pacman', 5.45, 3, 'Classic pacman game');
INSERT INTO TB_videogame(publisher, name, credits_price, PEGI, short_description) VALUES ('Namco', 'Little Nightmares', 9.99, 16, 'Really pretty and horific game');
INSERT INTO TB_videogame(publisher, name, credits_price, PEGI, short_description) VALUES ('Namco', 'Dark souls', 9.99, 16, 'Slash and slash and... die... Retry !');


INSERT INTO TB_videogame(publisher, name, credits_price, PEGI, short_description) VALUES ('Bioware', 'Baldur''s gate', 9.99, 16, 'You really need a description for this one ?');
INSERT INTO TB_videogame(publisher, name, credits_price, PEGI, short_description) VALUES ('Bioware', 'Baldur''s gate 2', 9.99, 16, 'Sequel to Baldur''s gate... What else to say ?');
INSERT INTO TB_videogame(publisher, name, credits_price, PEGI, short_description) VALUES ('Bioware', 'Mass effect', 14.99, 16, 'Mass Effect is a science fiction action role-playing third-person shooter video game series developed by the Canadian company BioWare and released for the Xbox 360, PlayStation 3 and Microsoft Windows');
