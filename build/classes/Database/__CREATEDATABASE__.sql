
--DROP DATABASE IF EXISTS resahh_company;
CREATE DATABASE resahh_company;
USE resahh_company;

CREATE TABLE roles(
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    role VARCHAR( 50 )
);

CREATE TABLE depths(
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    dept VARCHAR( 50 )
);

CREATE TABLE workers(
    id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR( 50 ),
    hiredate DATE,
    role_id INTEGER,
    dept_id INTEGER,
    FOREIGN KEY ( role_id ) REFERENCES roles( id ),
    FOREIGN KEY ( dept_id ) REFERENCES depths( id )
);

INSERT INTO roles ( role ) VALUES
( "Vezető"),
( "Munkás"),
( "Ellenőr"),
( "Külsős");

INSERT INTO depths ( dept ) VALUES
( "Titkárság" ),
( "Szerelőcsarnok" ),
( "Raktár" );

INSERT INTO workers (name, hiredate, role_id, dept_id ) VALUES
("Negye Dóra", "2018-12-25", 2, 3),
("Aloe Vera", "2018-03-10", 2, 2),
("Semmi Áron", "2018-03-11", 3, 3),
("Winch Eszter", "2018-04-02", 1, 1),
("Lev Elek", "2018-04-14", 1, 1),
("Ceglédik Anna", "2018-04-18", 2, 2),
("Szalmon Ella", "2018-04-20", 2, 2),
("Ultra Viola", "2018-04-20", 1, 1),
("Kala Pál", "2018-04-25", 2, 2),
("Rabsz Olga", "2018-05-02", 2, 2),
("Hát Izsák", "2018-05-08", 3, 3),
("Nemnyúl Kálmán", "2018-05-15", 2, 2),
("Mor Zsolt", "2018-05-15", 4, 2),
("Kasza Blanka", "2018-05-21", 2, 2),
("Külö Nóra", "2018-05-22", 2, 2),
("Fá Zoltán", "2021-04-03", 2, 2),
("Pólika Pál", "2021-04-03", 3, 2),
("Ria Dóra", "2020-08-14", 2, 2),
("Git Áron", "2022-11-26", 2, 2),
("Körm Ödön", "2018-01-20", 2, 2);