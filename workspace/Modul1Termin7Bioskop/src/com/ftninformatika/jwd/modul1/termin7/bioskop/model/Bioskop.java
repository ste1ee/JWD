package com.ftninformatika.jwd.modul1.termin7.bioskop.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Bioskop {

	private static final String DATA_FOLDER = "data";
	private static final Path ZANROVI_PATH = Paths.get(DATA_FOLDER, "zanrovi.csv");
	private static final Path FILMOVI_PATH = Paths.get(DATA_FOLDER, "filmovi.csv");
	private static final Path FILM_ZANR_PATH = Paths.get(DATA_FOLDER, "filmZanr.csv");
	private static final Path PROJEKCIJE_PATH = Paths.get(DATA_FOLDER, "projekcije.csv");
	
	private static final Map<Long, Zanr> zanrovi = new LinkedHashMap<>();
	private static final Map<Long, Film> filmovi = new LinkedHashMap<>();
	private static final Map<Long, Projekcija> projekcije = new LinkedHashMap<>();

	private static long maxZanrId = 0;
	private static long maxFilmId = 0;
	private static long maxProjekcijaId = 0;
	
	private static void inicijalizuj() {
		// kreiranje žanrova
		zanrovi.put(1L, new Zanr(1L, "naučna fantastika"));
		zanrovi.put(2L, new Zanr(2L, "akcija"));
		zanrovi.put(3L, new Zanr(3L, "komedija"));
		zanrovi.put(4L, new Zanr(4L, "horor"));
		zanrovi.put(5L, new Zanr(5L, "avantura"));

		// kreiranje filmova
		filmovi.put(1L, new Film(1L, "Avengers: Endgame", 182));
		filmovi.put(2L, new Film(2L, "Life", 110));
		filmovi.put(3L, new Film(3L, "It: Chapter 2", 170));
		filmovi.put(4L, new Film(4L, "Pirates of the Caribbean: Dead Men Tell No Tales", 153));

		// povezivanje filmova i žanrova
		filmovi.get(1L).addZanr(zanrovi.get(1L));
		filmovi.get(2L).addZanr(zanrovi.get(1L));
		filmovi.get(1L).addZanr(zanrovi.get(2L));
		filmovi.get(4L).addZanr(zanrovi.get(2L));
		filmovi.get(4L).addZanr(zanrovi.get(3L));
		filmovi.get(2L).addZanr(zanrovi.get(4L));
		filmovi.get(3L).addZanr(zanrovi.get(4L));
		filmovi.get(1L).addZanr(zanrovi.get(5L));
		filmovi.get(4L).addZanr(zanrovi.get(5L));

		// kreiranje i projekcija i povezivanje projekcija i filmova
		projekcije.put(1L, new Projekcija(1L, LocalDateTime.parse("2020-06-22T20:00:00"), filmovi.get(1L), 1, "2D", 380.00));
		projekcije.put(2L, new Projekcija(2L, LocalDateTime.parse("2020-06-22T23:30:00"), filmovi.get(3L), 1, "2D", 380.00));
		projekcije.put(3L, new Projekcija(3L, LocalDateTime.parse("2020-06-22T20:00:00"), filmovi.get(1L), 2, "3D", 420.00));
		projekcije.put(4L, new Projekcija(4L, LocalDateTime.parse("2020-06-22T23:30:00"), filmovi.get(2L), 2, "3D", 420.00));
		projekcije.put(5L, new Projekcija(5L, LocalDateTime.parse("2020-06-22T20:00:00"), filmovi.get(3L), 3, "4D", 580.00));
		projekcije.put(6L, new Projekcija(6L, LocalDateTime.parse("2020-06-23T20:00:00"), filmovi.get(2L), 1, "2D", 380.00));
		projekcije.put(7L, new Projekcija(7L, LocalDateTime.parse("2020-06-23T22:00:00"), filmovi.get(4L), 1, "2D", 380.00));
		projekcije.put(8L, new Projekcija(8L, LocalDateTime.parse("2020-06-23T20:00:00"), filmovi.get(2L), 2, "3D", 420.00));
		projekcije.put(9L, new Projekcija(9L, LocalDateTime.parse("2020-06-23T22:00:00"), filmovi.get(4L), 2, "3D", 420.00));
		projekcije.put(10L, new Projekcija(10L, LocalDateTime.parse("2020-06-23T20:00:00"), filmovi.get(1L),3, "4D", 580.00));
		
		maxZanrId = 5L;
		maxFilmId = 4L;
		maxProjekcijaId = 10L;
	}

	// funkcije za čuvanje
	private static void sacuvajZanrove() throws IOException {
		List<String> linije = new ArrayList<>();
		for (Zanr itZanr: zanrovi.values()) {
			String linija = String.join(",", 
				String.valueOf(itZanr.getId()),
				itZanr.getNaziv()
			);
			linije.add(linija);
		}
		Files.write(ZANROVI_PATH, linije);
	}
	
	private static void sacuvajFilmove() throws IOException {
		List<String> linije = new ArrayList<>();
		for (Film itFilm: filmovi.values()) {
			String linija = String.join(",", 
				String.valueOf(itFilm.getId()),
				itFilm.getNaziv(),
				String.valueOf(itFilm.getTrajanje())
			);
			linije.add(linija);
		}
		Files.write(FILMOVI_PATH, linije);
	}

	private static void sacuvajFilmZanr() throws IOException {
		List<String> linije = new ArrayList<>();
		for (Film itFilm: filmovi.values()) {
			for (Zanr itZanr: itFilm.getZanrovi()) {
				String linija = String.join(",", 
					String.valueOf(itFilm.getId()),
					String.valueOf(itZanr.getId())
				);
				linije.add(linija);
			}
		}
		Files.write(FILM_ZANR_PATH, linije);
	}
	
	private static void sacuvajProjekcije() throws IOException {
		List<String> linije = new ArrayList<>();
		for (Projekcija itProjekcija: projekcije.values()) {
			String linija = String.join(",", 
				String.valueOf(itProjekcija.getId()),
				itProjekcija.getDatumIVreme().toString(),
				String.valueOf(itProjekcija.getFilm().getId()),
				String.valueOf(itProjekcija.getSala()), 
				itProjekcija.getTip(), 
				String.valueOf(itProjekcija.getCenaKarte())
			);
			linije.add(linija);
		}
		Files.write(PROJEKCIJE_PATH, linije);
	}

	public static void sacuvaj() throws IOException {
		sacuvajZanrove();
		sacuvajFilmove();
		sacuvajFilmZanr();
		sacuvajProjekcije();
	}

	// funkcije za učitavanje
	private static void ucitajZanrove() throws IOException {
		for (String itLinija: Files.readAllLines(ZANROVI_PATH)) {
			String[] tokeni = itLinija.split(",");
			long id = Long.parseLong(tokeni[0]);
			String naziv = tokeni[1];
			
			Zanr zanr = new Zanr(id, naziv);
			zanrovi.put(zanr.getId(), zanr);
			
			maxZanrId = Math.max(maxZanrId, id + 1);
		}
	}

	private static void ucitajFilmove() throws IOException {
		for (String itLinija: Files.readAllLines(FILMOVI_PATH)) {
			String[] tokeni = itLinija.split(",");
			long id = Long.parseLong(tokeni[0]);
			String naziv = tokeni[1];
			int trajanje = Integer.parseInt(tokeni[2]);
			
			Film film = new Film(id, naziv, trajanje);
			filmovi.put(film.getId(), film);
			
			maxFilmId = Math.max(maxFilmId, id + 1);
		}
	}

	private static void ucitajFilmZanr() throws IOException {
		for (String itLinija: Files.readAllLines(FILM_ZANR_PATH)) {
			String[] tokeni = itLinija.split(",");
			long filmID = Long.parseLong(tokeni[0]);
			long zanrID = Long.parseLong(tokeni[1]);

			filmovi.get(filmID).addZanr(zanrovi.get(zanrID));
		}
	}
	
	private static void ucitajProjekcije() throws IOException {
		for (String itLinija: Files.readAllLines(PROJEKCIJE_PATH)) {
			String[] tokeni = itLinija.split(",");
			long id = Long.parseLong(tokeni[0]);
			LocalDateTime datumIVreme = LocalDateTime.parse(tokeni[1]);
			Film film = filmovi.get(Long.parseLong(tokeni[2]));
			int sala = Integer.parseInt(tokeni[3]);
			String tip = tokeni[4];
			double cena = Double.parseDouble(tokeni[5]);
			
			
			Projekcija projekcija = new Projekcija(id, datumIVreme, film, sala, tip, cena);
			projekcije.put(projekcija.getId(), projekcija);
			
			maxProjekcijaId = Math.max(maxProjekcijaId, id + 1);
		}
	}

	public static void ucitaj() throws IOException {
		try {
			// redosled učitavanja je bitan
			ucitajZanrove();
			ucitajFilmove();
			ucitajFilmZanr();
			ucitajProjekcije();
		} catch (IOException ex) {
			// u slučaju da datoteke nisu pronađene, model se reset-uje na početno stanje
			inicijalizuj();
			sacuvaj();

			ex.printStackTrace();
		}
	}

	public static Map<Long, Zanr> getZanrovi() {
		return zanrovi;
	}
	
	public static Map<Long, Film> getFilmovi() {
		return filmovi;
	}

	public static Map<Long, Projekcija> getProjekcije() {
		return projekcije;
	}

	public static long nextZanrId() {
		maxZanrId++;
		return maxZanrId;
	}

	public static long nextFilmId() {
		maxFilmId++;
		return maxFilmId;
	}

	public static long nextProjekcijaId() {
		maxProjekcijaId++;
		return maxProjekcijaId;
	}

}
