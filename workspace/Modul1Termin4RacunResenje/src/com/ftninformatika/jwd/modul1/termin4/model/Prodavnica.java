package com.ftninformatika.jwd.modul1.termin4.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;



public class Prodavnica {
	
	private static final String DATE_TIME_FORMAT = "dd.MM.yyyy. HH:mm";
	private static final String DATE_FORMAT = "dd.MM.yyyy.";

	static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
	static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

	private static final String DATA_FOLDER = "data";
	private static final Path KATEGORIJE_PATH = Paths.get(DATA_FOLDER, "kategorije.csv");
	private static final Path PROIZVODI_PATH = Paths.get(DATA_FOLDER, "proizvodi.csv");
	private static final Path RACUNI_PATH = Paths.get(DATA_FOLDER, "racuni.csv");
	private static final Path PROIZVOD_KATEGORIJA_PATH = Paths.get(DATA_FOLDER, "proizvodKategorija.csv");
	private static final Path STAVKE_PATH = Paths.get(DATA_FOLDER, "stavke.csv");
	private static final Path RACUN_STAVKA_PATH = Paths.get(DATA_FOLDER, "racunStavka.csv");
	
	private static final Path ADMINI_PATH = Paths.get(DATA_FOLDER, "admini.csv");
	private static final Path RADNICI_PATH = Paths.get(DATA_FOLDER, "radnici.csv");
	
	
	private static final Map<Long, Kategorija> kategorije = new HashMap<>();
	private static final Map<Long, Proizvod> proizvodi = new HashMap<>();
	private static final Map<Long, Racun> racuni = new HashMap<>();
	private static final Map<Long, Stavka> stavke = new HashMap<>();
	
	private static final Map<String, Korisnik> korisnici = new HashMap<>();

	private static long maxKategorijaId = 0;
	private static long maxProizvodId = 0;
	private static long maxRacunId = 0;
	private static long maxStavkaId = 0;
	
	private static void inicijalizuj() {
		
		// kreiranje kategorija
		Kategorija kategorija1 =  new Kategorija(1L, "mlecni proizvodi");
		Kategorija kategorija2 =  new Kategorija(2L, "voce i povrce");
		Kategorija kategorija3 =  new Kategorija(3L, "pekara");
		Kategorija kategorija4 =  new Kategorija(4L, "pakovano meso");
		Kategorija kategorija5 =  new Kategorija(5L, "delikates");
		
		// kreiranje proizvoda
		Proizvod proizvod1 = new Proizvod(1L, "Mleko", 100.0);
		Proizvod proizvod2 = new Proizvod(2L, "Mandarina", 130.0);
		Proizvod proizvod3 = new Proizvod(3L, "Francuska kifla", 80.0);
		Proizvod proizvod4 = new Proizvod(4L, "Mleveno meso", 550.0);
		Proizvod proizvod5 = new Proizvod(5L, "Zlatiborac prÅ¡uta", 689.0);
		
				
		// kreiranje racuna
		Racun racun1 = new Racun(1L, LocalDateTime.now(), 0.0);
		Racun racun2 = new Racun(2L, LocalDateTime.now(), 0.0);
		Racun racun3 = new Racun(3L, LocalDateTime.now(), 0.0);
		Racun racun4 = new Racun(4L, LocalDateTime.now(), 0.0);
		Racun racun5 = new Racun(5L, LocalDateTime.now(), 0.0);
		
		
		// kreiranje stavki 
		Stavka stavka1 = new Stavka(1L, proizvod1, 2);
		Stavka stavka2 = new Stavka(2L, proizvod2, 3);
		Stavka stavka3 = new Stavka(3L, proizvod3, 1);
		Stavka stavka4 = new Stavka(4L, proizvod4, 2);
		Stavka stavka5 = new Stavka(5L, proizvod5, 3);
		Stavka stavka6 = new Stavka(6L, proizvod1, 4);
		Stavka stavka7 = new Stavka(7L, proizvod2, 5);
		Stavka stavka8 = new Stavka(8L, proizvod3, 1);
		Stavka stavka9 = new Stavka(9L, proizvod4, 2);
		Stavka stavka10 = new Stavka(10L, proizvod5, 3);
		
		// povezivanja proizvoda i kategorije
		// obavezno je povezivanje, jer proizvod ne moze postojati samostalno
		// bez kategorije
		// veza 1..* izmedju Proizvoda i Kategorije
		proizvod1.addKategoriju(kategorija1);
		proizvod2.addKategoriju(kategorija2);
		proizvod3.addKategoriju(kategorija3);
		proizvod4.addKategoriju(kategorija4);
		proizvod5.addKategoriju(kategorija5);
		// povezivanje racuna i stavki 
		// opciono je povezivanje, jer racun moze postojati samostalno (ne zavisi od stavke)
		// veza 0..* izmedju Racuna i Stavke
		racun1.addStavku(stavka1);
		racun1.addStavku(stavka9);
		racun2.addStavku(stavka2);
		racun2.addStavku(stavka10);
		racun3.addStavku(stavka3);
		racun3.addStavku(stavka4);
		racun4.addStavku(stavka5);
		racun4.addStavku(stavka8);
		racun5.addStavku(stavka6);
		racun5.addStavku(stavka7);
		
		// smestanje objekata u kolekcije
		kategorije.put(1L, kategorija1);
		kategorije.put(2L, kategorija2);
		kategorije.put(3L, kategorija3);
		kategorije.put(4L, kategorija4);
		kategorije.put(5L, kategorija5);
		
		proizvodi.put(1L, proizvod1);
		proizvodi.put(2L, proizvod2);
		proizvodi.put(3L, proizvod3);
		proizvodi.put(4L, proizvod4);
		proizvodi.put(5L, proizvod5);
		
		racuni.put(1L, racun1);
		racuni.put(2L, racun2);
		racuni.put(3L, racun3);
		racuni.put(4L, racun4);
		racuni.put(5L, racun5);
		
		stavke.put(1L, stavka1);
		stavke.put(2L, stavka2);
		stavke.put(3L, stavka3);
		stavke.put(4L, stavka4);
		stavke.put(5L, stavka5);
		stavke.put(6L, stavka6);
		stavke.put(7L, stavka7);
		stavke.put(8L, stavka8);
		stavke.put(9L, stavka9);
		stavke.put(10L, stavka10);
		
		
		
		// drugi nacin za dodavanje
		
//		proizvodi.get(1L).addKategoriju(kategorija1);
//		proizvodi.get(2L).addKategoriju(kategorija2);
//		proizvodi.get(3L).addKategoriju(kategorija3);
//		proizvodi.get(4L).addKategoriju(kategorija4);
//		proizvodi.get(5L).addKategoriju(kategorija5);
//	
//		racuni.get(1L).addStavku(stavke.get(1L));
//		racuni.get(1L).addStavku(stavke.get(9L));
//		racuni.get(2L).addStavku(stavke.get(2L));
//		racuni.get(2L).addStavku(stavke.get(10L));
//		racuni.get(3L).addStavku(stavke.get(3L));
//		racuni.get(3L).addStavku(stavke.get(4L));
//		racuni.get(4L).addStavku(stavke.get(5L));
//		racuni.get(4L).addStavku(stavke.get(8L));
//		racuni.get(5L).addStavku(stavke.get(6L));
//		racuni.get(5L).addStavku(stavke.get(7L));

	    // upitno da li ide sve u fajl
		maxKategorijaId = 5L;
		maxProizvodId = 5L;
		maxRacunId = 5L;
		maxStavkaId = 10L;
	}

	// funkcije za Ä�uvanje
	private static void sacuvajKategorije() throws IOException {
		List<String> linije = new ArrayList<>();
		for (Kategorija itKategorija: kategorije.values()) {
			String linija = String.join(",", 
				String.valueOf(itKategorija.getId()),
				itKategorija.getNaziv()
			);
			linije.add(linija);
		}
		Files.write(KATEGORIJE_PATH, linije);
	}
	
	private static void sacuvajProizvode() throws IOException {
		
		List<String> linije = new ArrayList<>();
		for (Proizvod itProizvod: proizvodi.values()) {
			String linija = String.join(",", 
				String.valueOf(itProizvod.getId()),
				itProizvod.getNaziv(), String.valueOf(itProizvod.getCena()));
			linije.add(linija);
		}
		Files.write(PROIZVODI_PATH, linije);
	}
	
	private static void sacuvajProizvodeKategorije() throws IOException {

		List<String> linije = new ArrayList<>();
		for (Proizvod itProizvod: proizvodi.values()) {
			for (Kategorija itKategorija: itProizvod.getKategorije()) {
				String linija = String.join(",", 
					String.valueOf(itProizvod.getId()),
					String.valueOf(itKategorija.getId())
				);
				linije.add(linija);
			}
		}
		Files.write(PROIZVOD_KATEGORIJA_PATH, linije);
	}

	private static void sacuvajRacune() throws IOException {

		List<String> linije = new ArrayList<>();
		for (Racun itRacun: racuni.values()) {
			String linija = String.join(",", 
				String.valueOf(itRacun.getId()),
				DATE_TIME_FORMATTER.format(itRacun.getDatum()),
				String.valueOf(itRacun.getUkupnaCena()));
			linije.add(linija);
		}
		Files.write(RACUNI_PATH, linije);
	}
	
	private static void sacuvajStavke() throws IOException {
	
		List<String> linije = new ArrayList<>();
		for (Stavka itStavka: stavke.values()) {
			String linija = String.join(",", 
				String.valueOf(itStavka.getId()),
				String.valueOf(itStavka.getKolicina()),
			    String.valueOf(itStavka.proizvod.getId()));
			linije.add(linija);
		}
		Files.write(STAVKE_PATH, linije);
	}
	private static void sacuvajRacuneStavke() throws IOException {
		List<String> linije = new ArrayList<>();
		for (Racun itRacun: racuni.values()) {
			for (Stavka itStavka: itRacun.getStavke()) {
				String linija = String.join(",", 
					String.valueOf(itRacun.getId()),
					String.valueOf(itStavka.getId())
				);
				linije.add(linija);
			}
		}
		Files.write(RACUN_STAVKA_PATH, linije);
	}

	public static void sacuvaj() throws IOException {
		sacuvajKategorije();
		sacuvajProizvode();
		sacuvajProizvodeKategorije();
		sacuvajRacune();
		sacuvajStavke();
		sacuvajRacuneStavke();
	}

	// funkcije za uÄ�itavanje
	private static void ucitajRadnike() throws IOException {
		for(String linija : Files.readAllLines(RADNICI_PATH)) {
			String[] tokeni = linija.split(",");
			long id = Long.parseLong(tokeni[0]);
			String ime = tokeni[1];
			String prezime = tokeni[2];
			String korisnickoIme = tokeni[3];
			String lozinka = tokeni[4];
			String adresa = tokeni[5];
			LocalDate datumRegistracije = LocalDate.parse(tokeni[6], DATE_FORMATTER);
			
			Radnik radnik = new Radnik(id, ime, prezime, korisnickoIme, lozinka, adresa, datumRegistracije);
			korisnici.put(radnik.getKorisnickoIme(), radnik);
		}
	}
	
	private static void ucitajAdmine() throws IOException {
		for(String linija : Files.readAllLines(ADMINI_PATH)) {
			String[] tokeni = linija.split(",");
			long id = Long.parseLong(tokeni[0]);
			String ime = tokeni[1];
			String prezime = tokeni[2];
			String korisnickoIme = tokeni[3];
			String lozinka = tokeni[4];
			
			Admin admin = new Admin(id, ime, prezime, korisnickoIme, lozinka);
			korisnici.put(admin.getKorisnickoIme(), admin);
		}
	}
	
	private static void ucitajKorisnike() throws IOException {
		ucitajAdmine();
		ucitajRadnike();
	}
	
	private static void ucitajKategorije() throws IOException {

		for (String itLinija: Files.readAllLines(KATEGORIJE_PATH)) {
			String[] tokeni = itLinija.split(",");
			long id = Long.parseLong(tokeni[0]);
			String naziv = tokeni[1];
			
			Kategorija kategorija = new Kategorija(id, naziv);
			kategorije.put(kategorija.getId(), kategorija);
			
			maxKategorijaId++;
		}
	}

	private static void ucitajProizvode() throws IOException {
		

		for (String itLinija: Files.readAllLines(PROIZVODI_PATH)) {
			String[] tokeni = itLinija.split(",");
			long id = Long.parseLong(tokeni[0]);
			String naziv = tokeni[1];
			double ukupnaCena = Double.parseDouble(tokeni[2]);
			
			Proizvod proizvod = new Proizvod(id, naziv, ukupnaCena);
			proizvodi.put(proizvod.getId(), proizvod);
			
			maxProizvodId++;
		}
	}
	
	private static void ucitajProizvodeKategorije() throws IOException {		
		for (String itLinija: Files.readAllLines(PROIZVOD_KATEGORIJA_PATH)) {
			String[] tokeni = itLinija.split(",");
			long itProizvod = Long.parseLong(tokeni[0]);
			long itKategorija = Long.parseLong(tokeni[1]);
			proizvodi.get(itProizvod).addKategoriju(kategorije.get(itKategorija));
		}		
	}

	private static void ucitajRacune() throws IOException {		
		for (String itLinija: Files.readAllLines(RACUNI_PATH)) {
			String[] tokeni = itLinija.split(",");
			long id = Long.parseLong(tokeni[0]);
			LocalDateTime datumIVreme= LocalDateTime.parse(tokeni[1], DATE_TIME_FORMATTER);
			double cena = Double.parseDouble(tokeni[2]);
			Racun racun = new Racun(id, datumIVreme, cena);
			racuni.put(racun.getId(), racun);
			
			maxRacunId++;
		}
	}
	
	private static void ucitajStavke() throws IOException {
		for (String itLinija: Files.readAllLines(STAVKE_PATH)) {
			String[] tokeni = itLinija.split(",");
			long id = Long.parseLong(tokeni[0]);
			int kolicina = Integer.parseInt(tokeni[1]);
			long proizvodId = Long.parseLong(tokeni[2]);
			Proizvod proizvod  = proizvodi.get(proizvodId);
			Stavka stavka = new Stavka(id,proizvod, kolicina);
			stavke.put(stavka.getId(), stavka);
			maxStavkaId++;
		}
		
	}
	private static void ucitajRacuneStavke() throws IOException {

		for (String itLinija: Files.readAllLines(RACUN_STAVKA_PATH)) {
			String[] tokeni = itLinija.split(",");
			long racunID = Long.parseLong(tokeni[0]);
			long stavkaID = Long.parseLong(tokeni[1]);
			//spajamo racun sa stavkom
			racuni.get(racunID).addStavku(stavke.get(stavkaID));
		}
	}
		

	public static void ucitaj() throws IOException {
		try {
			ucitajKorisnike();
			// redosled uÄ�itavanja je bitan
			ucitajKategorije();
			ucitajProizvode();
			ucitajProizvodeKategorije();
			ucitajRacune();
			ucitajStavke();
			ucitajRacuneStavke();

		} catch (IOException ex) {
			// u sluÄ�aju da datoteke nisu pronaÄ‘ene, model se reset-uje na poÄ�etno stanje
			inicijalizuj();
			sacuvaj();

			ex.printStackTrace();
		}
	}
	public static Map<String, Korisnik> getKorisnici(){
		return korisnici;
	}

	public static Map<Long, Proizvod> getProizvod() {
		return proizvodi;
	}
	
	public static Map<Long, Racun> getRacuni() {
		return racuni;
	}

	public static Map<Long, Stavka> getStavke() {
		return stavke;
	}
	public static Map<Long, Kategorija> getKategorije() {
		return kategorije;
	}

	public static long nextKategorijaId() {
		maxKategorijaId++;
		return maxKategorijaId;
	}

	public static long nextProizvodId() {
		maxProizvodId++;
		return maxProizvodId;
	}

	public static long nextRacunId() {
		maxRacunId++;
		return maxRacunId;
	}
	public static long nextStavkaId() {
		maxStavkaId++;
		return maxStavkaId;
	}
}
