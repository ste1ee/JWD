DROP SCHEMA IF EXISTS vakcinisanje;
CREATE SCHEMA vakcinisanje DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE vakcinisanje;

CREATE TABLE vakcine(
	id BIGINT AUTO_INCREMENT,
    naziv VARCHAR(50) NOT NULL,
    tip VARCHAR(50) NOT NULL,
    tempCuvanja INT NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE prijave(
	id BIGINT AUTO_INCREMENT,
    jmbg VARCHAR(50) UNIQUE,
    imeIPrezime VARCHAR(50) NOT NULL,
    vakcinaId BIGINT,
    datum DATE,
    PRIMARY KEY(id),
    FOREIGN KEY(vakcinaId) REFERENCES vakcine(id)
);

INSERT INTO vakcine (id, naziv, tip, tempCuvanja) VALUES (1, 'Pfizer-BioNTech', 'RNK vakcina', -70);
INSERT INTO vakcine (id, naziv, tip, tempCuvanja) VALUES (2, 'Sputnik V', 'vakcina sa viralnim vektorom', 8);
INSERT INTO vakcine (id, naziv, tip, tempCuvanja) VALUES (3, 'Sinopharm', 'inaktivisana vakcina', 8);
INSERT INTO vakcine (id, naziv, tip, tempCuvanja) VALUES (4, 'AstraZeneca', 'vakcina sa viralnim vektorom', 8);
INSERT INTO vakcine (id, naziv, tip, tempCuvanja) VALUES (5, 'Moderna', 'RNK vakcina', -20);

INSERT INTO prijave (id, jmbg, imeIPrezime, vakcinaId, datum) VALUES (1, '1111111111111', 'Aaa Aaa', 1, '2021-01-01');
INSERT INTO prijave (id, jmbg, imeIPrezime, vakcinaId, datum) VALUES (2, '2222222222222', 'Bbb Bbb', 1, '2021-01-15');
INSERT INTO prijave (id, jmbg, imeIPrezime, vakcinaId, datum) VALUES (3, '3333333333333', 'Ccc Ccc', 2, '2021-02-01');
INSERT INTO prijave (id, jmbg, imeIPrezime, vakcinaId, datum) VALUES (4, '4444444444444', 'Ddd Ddd', 3, '2021-02-15');
INSERT INTO prijave (id, jmbg, imeIPrezime, vakcinaId, datum) VALUES (5, '5555555555555', 'Eee Eee', 2, '2021-03-01');
