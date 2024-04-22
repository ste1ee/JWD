DROP SCHEMA IF EXISTS sportpass;
CREATE SCHEMA sportpass DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE sportpass;

CREATE TABLE paketi(
	id BIGINT AUTO_INCREMENT,
    naziv VARCHAR(25) NOT NULL,
    brtr INT NOT NULL,
    meseci INT NOT NULL,
    cena DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE clanarine(
	id BIGINT AUTO_INCREMENT,
    paketId BIGINT,
    korisnik VARCHAR(50) NOT NULL,
    pocetak DATE,
    PRIMARY KEY(id),
    FOREIGN KEY(paketId) REFERENCES paketi(id)
);

INSERT INTO paketi (id, naziv, brtr, meseci, cena) VALUES (1, 'Basic S', 12, 1, 2800.00);
INSERT INTO paketi (id, naziv, brtr, meseci, cena) VALUES (2, 'Classic S', 16, 1, 3600.00);
INSERT INTO paketi (id, naziv, brtr, meseci, cena) VALUES (3, 'Elite S', 31, 1, 5200.00);
INSERT INTO paketi (id, naziv, brtr, meseci, cena) VALUES (4, 'Basic M', 12, 3, 6300.00);
INSERT INTO paketi (id, naziv, brtr, meseci, cena) VALUES (5, 'Classic M', 16, 3, 8100.00);
INSERT INTO paketi (id, naziv, brtr, meseci, cena) VALUES (6, 'Elite M', 31, 3, 11700.00);

INSERT INTO clanarine (id, paketId, korisnik, pocetak) VALUES (1, 1, 'korisnik1', '2023-01-01');
INSERT INTO clanarine (id, paketId, korisnik, pocetak) VALUES (2, 1, 'korisnik1', '2023-02-01');
INSERT INTO clanarine (id, paketId, korisnik, pocetak) VALUES (3, 1, 'korisnik2', '2023-02-01');
INSERT INTO clanarine (id, paketId, korisnik, pocetak) VALUES (4, 2, 'korisnik1', '2023-03-01');
INSERT INTO clanarine (id, paketId, korisnik, pocetak) VALUES (5, 3, 'korisnik2', '2023-03-01');
INSERT INTO clanarine (id, paketId, korisnik, pocetak) VALUES (6, 4, 'korisnik1', '2023-04-01');
INSERT INTO clanarine (id, paketId, korisnik, pocetak) VALUES (7, 4, 'korisnik2', '2023-04-01');
INSERT INTO clanarine (id, paketId, korisnik, pocetak) VALUES (8, 4, 'korisnik3', '2023-01-01');
INSERT INTO clanarine (id, paketId, korisnik, pocetak) VALUES (9, 5, 'korisnik3', '2023-04-01');
INSERT INTO clanarine (id, paketId, korisnik, pocetak) VALUES (10, 5, 'korisnik4', '2023-01-01');
INSERT INTO clanarine (id, paketId, korisnik, pocetak) VALUES (11, 5, 'korisnik4', '2023-04-01');
INSERT INTO clanarine (id, paketId, korisnik, pocetak) VALUES (12, 6, 'korisnik5', '2023-01-01');
