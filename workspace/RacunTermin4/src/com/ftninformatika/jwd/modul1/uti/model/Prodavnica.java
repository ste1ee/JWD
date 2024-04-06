package com.ftninformatika.jwd.modul1.uti.model;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Prodavnica {

	private static final String DATE_TIME_FORMAT = "dd.MM.yyyy. HH:mm";
	static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

	private static final String DATA_FOLDER = "data";
	private static final Path KATEGORIJE_PATH = Paths.get(DATA_FOLDER, "kategorije.csv");
	private static final Path PROIZVODI_PATH = Paths.get(DATA_FOLDER, "proizvodi.csv");
	private static final Path STAVKE_PATH = Paths.get(DATA_FOLDER, "stavke.csv");
	private static final Path RACUNI_PATH = Paths.get(DATA_FOLDER, "racuni.csv");
	private static final Path PROIZVODKATEGORIJA_PATH = Paths.get(DATA_FOLDER, "proizvodKategorija.csv");
	private static final Path RACUNSTAVKA_PATH = Paths.get(DATA_FOLDER, "racunStavka.csv");

	private static final Map<Long, Kategorija> kategorije = new HashMap<>();
	private static final Map<Long, Proizvod> proizvodi = new HashMap<>();
	private static final Map<Long, Stavka> stavke = new HashMap<>();
	private static final Map<Long, Racun> racuni = new HashMap<>();

	// za auto dodeljivanje ida
	private static long maxKategorijaId = 0;
	private static long maxProizvodId = 0;
	private static long maxStavkaId = 0;
	private static long maxRacunId = 0;

	private static void inicijalizuj() {

		Kategorija kategorija1 = new Kategorija(1, "Voce");
		Kategorija kategorija2 = new Kategorija(2, "Povrce");
		Kategorija kategorija3 = new Kategorija(3, "Mlecni proizvodi");
		Kategorija kategorija4 = new Kategorija(4, "Mesni proizvodi");
		Kategorija kategorija5 = new Kategorija(5, "Slatkisi");

		Proizvod proizvod1 = new Proizvod(1, "Jabuka KG", 79.99);
		Proizvod proizvod2 = new Proizvod(2, "Krompir KG", 99.99);
		Proizvod proizvod3 = new Proizvod(3, "Mleko L", 129.99);
		Proizvod proizvod4 = new Proizvod(4, "Mleveno Junece KG", 849.99);
		Proizvod proizvod5 = new Proizvod(5, "Mars", 89.99);

		Stavka stavka1 = new Stavka(1, 1);
		Stavka stavka2 = new Stavka(2, 2);
		Stavka stavka3 = new Stavka(3, 3);
		Stavka stavka4 = new Stavka(4, 4);
		Stavka stavka5 = new Stavka(5, 5);

		Racun racun1 = new Racun(1, LocalDateTime.now(), 0.0);
		Racun racun2 = new Racun(2, LocalDateTime.now(), 0.0);
		Racun racun3 = new Racun(3, LocalDateTime.now(), 0.0);
		Racun racun4 = new Racun(4, LocalDateTime.now(), 0.0);
		Racun racun5 = new Racun(5, LocalDateTime.now(), 0.0);

		// povezivanje
		proizvod1.addKategorija(kategorija1);
		proizvod2.addKategorija(kategorija2);
		proizvod3.addKategorija(kategorija3);
		proizvod4.addKategorija(kategorija4);
		proizvod5.addKategorija(kategorija5);

		// objekti u kolekcije
		kategorije.put((long) 1, kategorija1);
		kategorije.put((long) 2, kategorija2);
		kategorije.put((long) 3, kategorija3);
		kategorije.put((long) 4, kategorija4);
		kategorije.put((long) 5, kategorija5);

		proizvodi.put((long) 1, proizvod1);
		proizvodi.put((long) 2, proizvod2);
		proizvodi.put((long) 3, proizvod3);
		proizvodi.put((long) 4, proizvod4);
		proizvodi.put((long) 5, proizvod5);

		stavke.put((long) 1, stavka1);
		stavke.put((long) 2, stavka2);
		stavke.put((long) 3, stavka3);
		stavke.put((long) 4, stavka4);
		stavke.put((long) 5, stavka5);

		racuni.put((long) 1, racun1);
		racuni.put((long) 2, racun2);
		racuni.put((long) 3, racun3);
		racuni.put((long) 4, racun4);
		racuni.put((long) 5, racun5);

		maxKategorijaId = 5L;
		maxProizvodId = 5L;
		maxStavkaId = 5L;
		maxRacunId = 5L;

	}

	private static void sacuvajKategorije() throws IOException {
		List<String> linije = new ArrayList<>();
		for (Kategorija k : kategorije.values()) {
			String linija = String.join(",", String.valueOf(k.getId()), k.getNaziv());
			linije.add(linija);
		}
		Files.write(KATEGORIJE_PATH, linije);

	}

	private static void sacuvajProizvode() throws IOException {
		List<String> linije = new ArrayList<>();
		for (Proizvod p : proizvodi.values()) {
			String linija = String.join(",", String.valueOf(p.getId()), p.getNaziv(), String.valueOf(p.getCena()));
			linije.add(linija);
		}
		Files.write(PROIZVODI_PATH, linije);
	}

	private static void sacuvajProizvodKategorija() throws IOException {

		List<String> linije = new ArrayList<>();
		for (Proizvod p : proizvodi.values()) {
			for (Kategorija k : p.getKategorije()) {
				String linija = String.join(",", String.valueOf(p.getId()), String.valueOf(k.getId()));
				linije.add(linija);

			}
		}
		Files.write(PROIZVODKATEGORIJA_PATH, linije);
	}

	private static void sacuvajStavke() throws IOException {

		List<String> linije = new ArrayList<>();
		for (Stavka s : stavke.values()) {
			String linija = String.join(",", String.valueOf(s.getId()), String.valueOf(s.getKolicina()),
					String.valueOf(s.getProizvod().getId()));
			linije.add(linija);
		}
		Files.write(STAVKE_PATH, linije);
	}

	private static void sacuvajRacun() throws IOException {

		List<String> linije = new ArrayList<>();
		for (Racun r : racuni.values()) {
			String linija = String.join(",", String.valueOf(r.getId()), String.valueOf(r.getDatum()),
					String.valueOf(r.getUkupnaCena()));
			linije.add(linija);
		}
		Files.write(RACUNI_PATH, linije);
	}

	private static void sacuvajRacunStavka() throws IOException {
		List<String> linije = new ArrayList<>();
		for (Racun r : racuni.values()) {
			for (Stavka s : r.getStavke()) {
				String linija = String.join(",", String.valueOf(r.getId()), String.valueOf(s.getId()));
				linije.add(linija);
			}
		}
		Files.write(RACUNSTAVKA_PATH, linije);
	}

	public static void sacuvaj() throws IOException {
		sacuvajKategorije();
		sacuvajProizvode();
		sacuvajProizvodKategorija();
		sacuvajStavke();
		sacuvajRacun();
	}

	private static void ucitajKategorije() throws IOException {
		for (String l : Files.readAllLines(KATEGORIJE_PATH)) {
			String[] tokeni = l.split(",");
			long id = Long.parseLong(tokeni[0]);
			String naziv = tokeni[1];

			Kategorija kategorija = new Kategorija(id, naziv);
			kategorije.put(kategorija.getId(), kategorija);

			maxKategorijaId++;
		}
	}

	private static void ucitajProizvode() throws IOException {
		for (String l : Files.readAllLines(PROIZVODI_PATH)) {
			String[] tokeni = l.split(",");
			long id = Long.parseLong(tokeni[0]);
			String naziv = tokeni[1];
			double cena = Double.parseDouble(tokeni[2]);

			Proizvod proizvod = new Proizvod(id, naziv, cena);
			proizvodi.put(proizvod.getId(), proizvod);

			maxProizvodId++;
		}
	}

	private static void ucitajProizvodKategorija() throws IOException {
		for (String l : Files.readAllLines(PROIZVODKATEGORIJA_PATH)) {
			String[] tokeni = l.split(",");
			long p = Long.parseLong(tokeni[0]);
			long k = Long.parseLong(tokeni[1]);
			proizvodi.get(p).addKategorija(kategorije.get(k));
		}
	}

	private static void ucitajStavke() throws IOException {
		for (String l : Files.readAllLines(STAVKE_PATH)) {
			String[] tokeni = l.split(",");
			long id = Long.parseLong(tokeni[0]);
			int kolicina = Integer.parseInt(tokeni[1]);
			long proizvodId = Long.parseLong(tokeni[2]);
			Proizvod proizvod = proizvodi.get(proizvodId);
			Stavka stavka = new Stavka(id, kolicina);
			stavke.put(stavka.getId(), stavka);

			maxStavkaId++;
		}
	}

	private static void ucitajRacune() throws IOException {
		for (String l : Files.readAllLines(RACUNI_PATH)) {
			String[] tokeni = l.split(",");
			long id = Long.parseLong(tokeni[0]);
			LocalDateTime datumIVreme = LocalDateTime.parse(tokeni[1], DATE_TIME_FORMATTER);
			double ukupnaCena = Double.parseDouble(tokeni[2]);
			Racun racun = new Racun(id, datumIVreme, ukupnaCena);
			racuni.put(racun.getId(), racun);

			maxRacunId++;
		}
	}

	private static void ucitajRacunStavka() throws IOException {
		for (String l : Files.readAllLines(RACUNSTAVKA_PATH)) {
			String[] tokeni = l.split(",");
			long racunId = Long.parseLong(tokeni[0]);
			long stavkaId = Long.parseLong(tokeni[1]);
			racuni.get(racunId).addStavka(stavke.get(stavkaId));
		}
	}

	public static void ucitaj() throws IOException {
		try {
			ucitajKategorije();
			ucitajProizvode();
			ucitajProizvodKategorija();
			ucitajStavke();
			ucitajRacune();
			ucitajRacunStavka();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			inicijalizuj();
			sacuvaj();

			e.printStackTrace();
		}
	}

	public static Map<Long, Kategorija> getKategorije() {
		return kategorije;
	}

	public static Map<Long, Proizvod> getProizvodi() {
		return proizvodi;
	}

	public static Map<Long, Stavka> getStavke() {
		return stavke;
	}

	public static Map<Long, Racun> getRacuni() {
		return racuni;
	}

	public static long nextKategorijaId() {
		maxKategorijaId++;
		return maxKategorijaId;
	}

	public static long nextProizvodId() {
		maxProizvodId++;
		return maxProizvodId;
	}

	public static long nextStavkaId() {
		maxStavkaId++;
		return maxStavkaId;
	}

	public static long nextRacunId() {
		maxRacunId++;
		return maxRacunId;
	}

}
