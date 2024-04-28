DROP SCHEMA IF EXISTS telefonija;
CREATE SCHEMA telefonija DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE telefonija;

CREATE TABLE tarife(
	id BIGINT AUTO_INCREMENT,
    naziv VARCHAR(50) NOT NULL,
    opis VARCHAR(150) NOT NULL,
    cena DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE pretplate(
	id BIGINT AUTO_INCREMENT,
    tarifaId BIGINT,
    broj VARCHAR(50) NOT NULL,
    datum DATE,
    trajanje INT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(tarifaId) REFERENCES tarife(id)
);

INSERT INTO tarife (id, naziv, opis, cena) VALUES (1, 'Potok 150', '150 min. 150 SMS, 2GB', 800);
INSERT INTO tarife (id, naziv, opis, cena) VALUES (2, 'Potok 300', '300 min. 300 SMS, 2.5GB', 1200);
INSERT INTO tarife (id, naziv, opis, cena) VALUES (3, 'Maslačak 12', 'neograničeni min., neograničeni SMS, 27GB', 1900);
INSERT INTO tarife (id, naziv, opis, cena) VALUES (4, 'Maslačak 25', 'neograničeni min., neograničeni SMS, 40GB', 2300);
INSERT INTO tarife (id, naziv, opis, cena) VALUES (5, 'Maslačak 50', 'neograničeni min., neograničeni SMS, 100GB', 2600);
INSERT INTO tarife (id, naziv, opis, cena) VALUES (6, 'Vrabac lite', 'neograničeni min., neograničeni SMS, neograničeni GB', 3000);
INSERT INTO tarife (id, naziv, opis, cena) VALUES (7, 'Vrabac max', 'neograničeni min., neograničeni SMS, neograničeni GB, besplatni Phoebus', 4500);

INSERT INTO pretplate (id, tarifaId, broj, datum, trajanje) VALUES (1, 1, '+381641111111', '2020-01-01', 12);
INSERT INTO pretplate (id, tarifaId, broj, datum, trajanje) VALUES (2, 1, '+381642222222', '2020-06-01', 36);
INSERT INTO pretplate (id, tarifaId, broj, datum, trajanje) VALUES (3, 2, '+381643333333', '2020-01-01', 24);
INSERT INTO pretplate (id, tarifaId, broj, datum, trajanje) VALUES (4, 2, '+381644444444', '2022-06-01', 12);
INSERT INTO pretplate (id, tarifaId, broj, datum, trajanje) VALUES (5, 3, '+381645555555', '2022-01-01', 24);
INSERT INTO pretplate (id, tarifaId, broj, datum, trajanje) VALUES (6, 4, '+381641111111', '2021-01-01', 12);
INSERT INTO pretplate (id, tarifaId, broj, datum, trajanje) VALUES (7, 4, '+381643333333', '2022-01-01', 12);
INSERT INTO pretplate (id, tarifaId, broj, datum, trajanje) VALUES (8, 4, '+381646666666', '2022-06-01', 24);
INSERT INTO pretplate (id, tarifaId, broj, datum, trajanje) VALUES (9, 4, '+381647777777', '2022-01-01', 36);
INSERT INTO pretplate (id, tarifaId, broj, datum, trajanje) VALUES (10, 5, '+381641111111', '2022-01-01', 24);
INSERT INTO pretplate (id, tarifaId, broj, datum, trajanje) VALUES (11, 5, '+381648888888', '2023-01-01', 36);
INSERT INTO pretplate (id, tarifaId, broj, datum, trajanje) VALUES (12, 5, '+381647777777', '2023-01-01', 36);
INSERT INTO pretplate (id, tarifaId, broj, datum, trajanje) VALUES (13, 6, '+381643333333', '2023-01-01', 12);
INSERT INTO pretplate (id, tarifaId, broj, datum, trajanje) VALUES (14, 6, '+381642222222', '2023-01-01', 24);
INSERT INTO pretplate (id, tarifaId, broj, datum, trajanje) VALUES (15, 6, '+381645555555', '2023-01-01', 12);
