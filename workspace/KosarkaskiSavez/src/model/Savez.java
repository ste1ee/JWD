package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Savez {
	private static final String DATA_FOLDER = "data";
	private static final Path IGRAC_PATH = Paths.get(DATA_FOLDER, "igraci.csv");
	private static final Path KLUB_PATH = Paths.get(DATA_FOLDER, "klubovi.csv");
	private static final Path SUDIJA_PATH = Paths.get(DATA_FOLDER, "sudije.csv");
	private static final Path UTAKMICA_PATH = Paths.get(DATA_FOLDER, "utakmice.csv");

	private static final Map<Integer, Igrac> igraci = new HashMap<>();
	private static final Map<Integer, Klub> klubovi = new HashMap<>();
	private static final Map<Integer, Sudija> sudije = new HashMap<>();
	private static final Map<Integer, Utakmica> utakmice = new HashMap<>();

	private static int maxIgracSifra = 0;
	private static int maxKlubSifra = 0;
	private static int maxSudijaSifra = 0;
	private static int maxUtakmicaSifra = 0;

	// metode za cuvanje u fajl
	private static void sacuvajIgrace() throws IOException {
		List<String> linije = new ArrayList<>();
		for (Igrac i : igraci.values()) {
			String linija = String.join(",", String.valueOf(i.getSifra()), String.valueOf(i.getIme()),
					String.valueOf(i.getPrezime()));
			linije.add(linija);
		}
		Files.write(IGRAC_PATH, linije);
	}

	private static void sacuvajKlubove() throws IOException {
		List<String> linije = new ArrayList<>();
		for (Klub k : klubovi.values()) {
			String linija = String.join(",", String.valueOf(k.getSifra()), String.valueOf(k.getNaziv()));
			linije.add(linija);
		}
		Files.write(KLUB_PATH, linije);
	}

	private static void sacuvajSudije() throws IOException {
		List<String> linije = new ArrayList<>();
		for (Sudija s : sudije.values()) {
			String linija = String.join(",", String.valueOf(s.getSifra()), String.valueOf(s.getIme()),
					String.valueOf(s.getPrezime()));
			linije.add(linija);
		}
		Files.write(SUDIJA_PATH, linije);
	}

	private static void sacuvajUtakmice() throws IOException {
		List<String> linije = new ArrayList<>();
		for (Utakmica u : utakmice.values()) {
			String linija = String.join(",", String.valueOf(u.getSifra()), String.valueOf(u.getDatum()),
					String.valueOf(u.getVreme()), String.valueOf(u.getDomacin().getSifra()),
					String.valueOf(u.getGost().getSifra()), String.valueOf(u.getSveSudije()));
			linije.add(linija);
		}
		Files.write(UTAKMICA_PATH, linije);
	}

	public static void sacuvaj() throws IOException {
		sacuvajIgrace();
		sacuvajKlubove();
		sacuvajSudije();
		sacuvajUtakmice();
	}

	// metode za citanje iz fajla
	private static void ucitajIgrace() throws IOException {
		for (String l : Files.readAllLines(IGRAC_PATH)) {
			String[] tokeni = l.split(",");
			int sifra = Integer.parseInt(tokeni[0]);
			String ime = tokeni[1];
			String prezime = tokeni[2];

			Igrac igrac = new Igrac(sifra, ime, prezime);
			igraci.put(igrac.getSifra(), igrac);

			maxIgracSifra++;
		}
	}

	private static void ucitajKlubove() throws IOException {
		for (String l : Files.readAllLines(KLUB_PATH)) {
			String[] tokeni = l.split(",");
			int sifra = Integer.parseInt(tokeni[0]);
			String naziv = tokeni[1];

			Klub klub = new Klub(sifra, naziv);
			klubovi.put(klub.getSifra(), klub);

			maxKlubSifra++;
		}
	}

	private static void ucitajSudije() throws IOException {
		for (String l : Files.readAllLines(SUDIJA_PATH)) {
			String[] tokeni = l.split(",");
			int sifra = Integer.parseInt(tokeni[0]);
			String ime = tokeni[1];
			String prezime = tokeni[2];

			Sudija sudija = new Sudija(sifra, ime, prezime);
			sudije.put(sudija.getSifra(), sudija);

			maxSudijaSifra++;
		}
	}

	private static void ucitajUtakmice() throws IOException {
		for (String l : Files.readAllLines(UTAKMICA_PATH)) {
			String[] tokeni = l.split(",");
			int sifra = Integer.parseInt(tokeni[0]);
			String datum = tokeni[1];
			String vreme = tokeni[2];
			int sifraDomacina = Integer.parseInt(tokeni[3]);
			int sifraGosta = Integer.parseInt(tokeni[4]);
			ArrayList<String> sveSudije = new ArrayList<String>(Arrays.asList(tokeni[5].split(",")));

			Klub domacin = klubovi.get(sifraDomacina);
			Klub gost = klubovi.get(sifraGosta);

			//Utakmica utakmica = new Utakmica(sifra, datum, vreme, domacin, gost,
			//sveSudije);
			//utakmice.put(utakmica.getSifra(), utakmica);

			maxUtakmicaSifra++;
		}
	}

	public static void ucitaj() throws IOException {
		ucitajIgrace();
		ucitajKlubove();
		ucitajSudije();
		ucitajUtakmice();
	}

	public static Map<Integer, Igrac> getIgraci() {
		return igraci;
	}

	public static Map<Integer, Klub> getKlubovi() {
		return klubovi;
	}

	public static Map<Integer, Sudija> getSudije() {
		return sudije;
	}

	public static Map<Integer, Utakmica> getUtakmice() {
		return utakmice;
	}

	public static int nextIgracSifra() {
		maxIgracSifra++;
		return maxIgracSifra;
	}

	public static int nextKlubSifra() {
		maxKlubSifra++;
		return maxKlubSifra;
	}

	public static int nextSudijaSifra() {
		maxSudijaSifra++;
		return maxSudijaSifra;
	}

	public static int nextUtakmicaSifra() {
		maxUtakmicaSifra++;
		return maxUtakmicaSifra;
	}

}
