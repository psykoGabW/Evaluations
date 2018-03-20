/*
Initialisation of database

WARNING : Existing tables will be dropped by this script !

*/
BEGIN; -- Start transaction
/*
Creation of database schema to isolate program tables
Important Note :
  You should use lowercase named schema because of postgresql database.
*/
CREATE SCHEMA IF NOT EXISTS appstore;

SET search_path TO appstore;

DROP TABLE IF EXISTS TB_purchased_videogame;
DROP TABLE IF EXISTS TB_videogame;
DROP TABLE IF EXISTS TB_credit_sale;
DROP TABLE IF EXISTS TB_customer;

/* Video games table
*/
CREATE TABLE TB_videogame(
  pk_id Serial PRIMARY KEY,
  publisher VARCHAR(25),
  name  VARCHAR(50) NOT NULL,
  credits_price  DECIMAL(5,2) NOT NULL,
  PEGI INTEGER,
  short_description VARCHAR(256),
  full_description TEXT,
  -- fk_publisher_id INTEGER REFERENCES APPStore.TB_Publisher(pk_id),
  website_url   VARCHAR(512)
);

CREATE TABLE TB_customer(
  pk_id Serial PRIMARY KEY,
  firstname VARCHAR(50) NOT NULL,
  lastname  VARCHAR(50) NOT NULL,
  email     VARCHAR(255) NOT NULL UNIQUE,
  knickname VARCHAR(25) NOT NULL UNIQUE,
  birthdate DATE NOT NULL,
  credits   DECIMAL(6,2) DEFAULT 0.00,
  CONSTRAINT valid_credits CHECK (credits >= 0)
);


CREATE TABLE TB_purchased_videogame(
  id Serial UNIQUE, -- This id is a "secondary" key used to respect CRUD pattern and allow easier implementation of abstract DAO<T> class
  fk_customer_id  INTEGER NOT NULL REFERENCES APPStore.TB_customer(pk_id),
  fk_videogame_id INTEGER NOT NULL REFERENCES APPStore.TB_videogame(pk_id),
  purchase_time   TIMESTAMP WITH TIME ZONE NOT NULL,
  credits_price    DECIMAL(5,2) NOT NULL CHECK (credits_price >=0), -- credits_price is store because price of TB_VIDEOGAME may change in time.
  CONSTRAINT CONSTRAINT_PURCHASED_GAME_PK PRIMARY KEY(FK_Customer_id, FK_Videogame_id)
);

/*
Video games can only be purchased by using application credits.
Those are sold to customers.
*/
CREATE TABLE TB_credit_sale(
  pk_id   SERIAL PRIMARY KEY,
  number  DECIMAL(5,2)  NOT NULL,
  fk_customer_id  INTEGER NOT NULL,
  sale_time TIMESTAMP WITH TIME ZONE NOT NULL,
  CONSTRAINT CONSTRAINT_CREDIT_SALE_FK_CUSTOMER FOREIGN KEY (fk_customer_id) REFERENCES APPStore.TB_customer(pk_id)
);

COMMIT;
