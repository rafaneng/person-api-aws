DROP TABLE IF EXISTS person;
CREATE TABLE IF NOT EXISTS person
(
    id numeric(19,0) NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL,
    date_created timestamp NOT NULL
);

DROP SEQUENCE IF EXISTS s_person;
CREATE SEQUENCE IF NOT EXISTS s_person
    CYCLE
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9999999999999
    CACHE 1;