DROP SCHEMA IF EXISTS voz;
CREATE SCHEMA voz DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE voz;

CREATE TABLE vozovi(
	id BIGINT AUTO_INCREMENT,
    broj VARCHAR(50) NOT NULL,
    naziv VARCHAR(50) NOT NULL,
    dIVPolaska DATETIME,
    cenaKarte DECIMAL(10, 2) NOT NULL,
    brojMesta INT NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE karte(
	id BIGINT AUTO_INCREMENT,
    vozId BIGINT,
    dIVProdaje DATETIME,
    kupac VARCHAR(50) NOT NULL,
    razred INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(vozId) REFERENCES vozovi(id)
);

INSERT INTO vozovi (id, broj, naziv, dIVPolaska, cenaKarte, brojMesta) VALUES (1, '2405', 'Regio', '2022-05-06 07:43', 600.00, 5);
INSERT INTO vozovi (id, broj, naziv, dIVPolaska, cenaKarte, brojMesta) VALUES (2, '543', 'Soko', '2022-05-06 13:00', 1000.00, 5);
INSERT INTO vozovi (id, broj, naziv, dIVPolaska, cenaKarte, brojMesta) VALUES (3, '749', 'Brzi', '2022-05-06 21:30', 800.00, 5);
INSERT INTO vozovi (id, broj, naziv, dIVPolaska, cenaKarte, brojMesta) VALUES (4, '541', 'Soko', '2022-05-07 07:04', 1000.00, 5);
INSERT INTO vozovi (id, broj, naziv, dIVPolaska, cenaKarte, brojMesta) VALUES (5, '2409', 'Regio', '2022-05-07 10:30', 600.00, 5);
INSERT INTO vozovi (id, broj, naziv, dIVPolaska, cenaKarte, brojMesta) VALUES (6, '749', 'Brzi', '2022-05-07 21:30', 800.00, 5);
INSERT INTO vozovi (id, broj, naziv, dIVPolaska, cenaKarte, brojMesta) VALUES (7, '2411', 'Regio', '2022-05-08 11:30', 600.00, 5);
INSERT INTO vozovi (id, broj, naziv, dIVPolaska, cenaKarte, brojMesta) VALUES (8, '553', 'Soko', '2022-05-08 14:00', 1000.00, 5);
INSERT INTO vozovi (id, broj, naziv, dIVPolaska, cenaKarte, brojMesta) VALUES (9, '749', 'Brzi', '2022-05-08 21:30', 800.00, 5);

INSERT INTO karte (id, vozId, dIVProdaje, kupac, razred) VALUES (1, 1, '2022-05-01 10:00', 'Kupac 1', 1);
INSERT INTO karte (id, vozId, dIVProdaje, kupac, razred) VALUES (2, 2, '2022-05-01 12:00', 'Kupac 2', 1);
INSERT INTO karte (id, vozId, dIVProdaje, kupac, razred) VALUES (3, 1, '2022-05-01 14:00', 'Kupac 3', 2);
INSERT INTO karte (id, vozId, dIVProdaje, kupac, razred) VALUES (4, 3, '2022-05-01 16:00', 'Kupac 4', 1);
INSERT INTO karte (id, vozId, dIVProdaje, kupac, razred) VALUES (5, 1, '2022-05-02 11:00', 'Kupac 5', 1);
INSERT INTO karte (id, vozId, dIVProdaje, kupac, razred) VALUES (6, 4, '2022-05-02 13:00', 'Kupac 6', 2);
INSERT INTO karte (id, vozId, dIVProdaje, kupac, razred) VALUES (7, 1, '2022-05-02 15:00', 'Kupac 7', 1);
INSERT INTO karte (id, vozId, dIVProdaje, kupac, razred) VALUES (8, 5, '2022-05-02 17:00', 'Kupac 8', 1);
INSERT INTO karte (id, vozId, dIVProdaje, kupac, razred) VALUES (9, 2, '2022-05-03 12:00', 'Kupac 9', 2);
INSERT INTO karte (id, vozId, dIVProdaje, kupac, razred) VALUES (10, 7, '2022-05-03 14:00', 'Kupac 10', 1);
INSERT INTO karte (id, vozId, dIVProdaje, kupac, razred) VALUES (11, 2, '2022-05-03 16:00', 'Kupac 11', 2);
INSERT INTO karte (id, vozId, dIVProdaje, kupac, razred) VALUES (12, 8, '2022-05-03 18:00', 'Kupac 12', 1);
INSERT INTO karte (id, vozId, dIVProdaje, kupac, razred) VALUES (13, 2, '2022-05-04 13:00', 'Kupac 13', 1);
INSERT INTO karte (id, vozId, dIVProdaje, kupac, razred) VALUES (14, 9, '2022-05-04 15:00', 'Kupac 14', 2);
INSERT INTO karte (id, vozId, dIVProdaje, kupac, razred) VALUES (15, 3, '2022-05-04 17:00', 'Kupac 15', 1);
INSERT INTO karte (id, vozId, dIVProdaje, kupac, razred) VALUES (16, 4, '2022-05-04 19:00', 'Kupac 16', 1);
INSERT INTO karte (id, vozId, dIVProdaje, kupac, razred) VALUES (17, 3, '2022-05-05 14:00', 'Kupac 17', 2);
INSERT INTO karte (id, vozId, dIVProdaje, kupac, razred) VALUES (18, 5, '2022-05-05 16:00', 'Kupac 18', 2);
INSERT INTO karte (id, vozId, dIVProdaje, kupac, razred) VALUES (19, 3, '2022-05-05 18:00', 'Kupac 19', 1);
INSERT INTO karte (id, vozId, dIVProdaje, kupac, razred) VALUES (20, 6, '2022-05-05 20:00', 'Kupac 20', 1);
