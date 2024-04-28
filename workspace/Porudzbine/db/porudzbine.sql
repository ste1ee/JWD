DROP SCHEMA IF EXISTS porudzbine;
CREATE SCHEMA porudzbine DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE porudzbine;

CREATE TABLE proizvodi(
	id BIGINT AUTO_INCREMENT,
    sifra VARCHAR(50) NOT NULL,
    naziv VARCHAR(150) NOT NULL,
    cena DECIMAL(10, 2) NOT NULL,
    besplatnadostava BOOLEAN,
    PRIMARY KEY(id)
);

CREATE TABLE porudzbine(
	id BIGINT AUTO_INCREMENT,
    datum DATETIME,
    ulica VARCHAR(50) NOT NULL,
    broj INT NOT NULL,
    proizvodId BIGINT,
    PRIMARY KEY(id),
    FOREIGN KEY(proizvodId) REFERENCES proizvodi(id)
);

INSERT INTO proizvodi (id, sifra, naziv, cena, besplatnadostava) VALUES (1, '0001', 'Proizvod 1', 5000.00, false);
INSERT INTO proizvodi (id, sifra, naziv, cena, besplatnadostava) VALUES (2, '0002', 'Proizvod 2', 100000.00, false);
INSERT INTO proizvodi (id, sifra, naziv, cena, besplatnadostava) VALUES (3, '0003', 'Proizvod 3', 15000.00, true);

INSERT INTO porudzbine (id, datum, ulica, broj, proizvodId) VALUES (1, '2021-09-01 10:00', 'Bul. Oslobođenja', 10, 1);
INSERT INTO porudzbine (id, datum, ulica, broj, proizvodId) VALUES (2, '2021-09-01 14:00', 'Bul. Mihajla Pupina', 10, 1);
INSERT INTO porudzbine (id, datum, ulica, broj, proizvodId) VALUES (3, '2021-09-01 20:00', 'Bul. Oslobođenja', 20, 2);
INSERT INTO porudzbine (id, datum, ulica, broj, proizvodId) VALUES (4, '2021-09-02 10:30', 'Bul. Cara Lazara', 10, 1);
INSERT INTO porudzbine (id, datum, ulica, broj, proizvodId) VALUES (5, '2021-09-02 14:30', 'Bul. Oslobođenja', 30, 3);
INSERT INTO porudzbine (id, datum, ulica, broj, proizvodId) VALUES (6, '2021-09-03 10:00', 'Bul. Mihajla Pupina', 20, 2);
INSERT INTO porudzbine (id, datum, ulica, broj, proizvodId) VALUES (7, '2021-09-03 14:00', 'Bul. Mihajla Pupina', 30, 3);
INSERT INTO porudzbine (id, datum, ulica, broj, proizvodId) VALUES (8, '2021-09-03 20:00', 'Bul. Cara Lazara', 20, 1);
