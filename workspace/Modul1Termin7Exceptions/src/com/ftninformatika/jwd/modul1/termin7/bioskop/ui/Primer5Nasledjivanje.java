package com.ftninformatika.jwd.modul1.termin7.bioskop.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

import com.ftninformatika.jwd.modul1.termin7.bioskop.model.Film;
import com.ftninformatika.jwd.modul1.util.Konzola;

public class Primer5Nasledjivanje {

	private static final File ISPRAVNA = Paths.get("data", "filmovi.csv").toFile();
	private static final File NEPOSTOJECA = Paths.get("data", "filmovi_nepostojeci.csv").toFile();
	private static final File OSTECENA = Paths.get("data", "filmovi_osteceni.csv").toFile();

	//public static Collection<Film> ucitaj() { // checked izuzeci moraju da se navode pri propagaciji
	//public static Collection<Film> ucitaj() throws FileNotFoundException, IOException { // unchecked izuzeci ne moraju da se navode pri propagaciji
	public static Collection<Film> ucitaj() throws FileNotFoundException, IOException, IndexOutOfBoundsException, NumberFormatException {
		Collection<Film> filmovi = new ArrayList<>();

		try (BufferedReader in = new BufferedReader(new FileReader(ISPRAVNA))) { // ova linija može da proizvede FileNotFoundException (checked)				
			String line = in.readLine(); // ova linija može da proizvede IOException (checked)
			while (line != null) {
				String[] tokeni = line.split(",");
				long id = Long.parseLong(tokeni[0]); // ova linija može da proizvede IndexOutOfBoundsException (unchecked) i NumberFormatException (unchecked)
				String naziv = tokeni[1]; // ova linija može da proizvede IndexOutOfBoundsException
				int trajanje = Integer.parseInt(tokeni[2]); // ova linija može da proizvede IndexOutOfBoundsException (unchecked) i NumberFormatException (unchecked)
				
				Film film = new Film(id, naziv, trajanje);
				filmovi.add(film);
				
				line = in.readLine(); // ova linija može da proizvede IOException (checked)
			}
		}
		return filmovi;
	}
	
	private static void prolazak(Collection<Film> filmovi) {
		System.out.println();
		for (Film itFilm: filmovi) {
			System.out.println(itFilm);
		}
	}
	
	public static void main(String[] args) {
		do {	
			try {	
				Collection<Film> filmovi = ucitaj();
				prolazak(filmovi);
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
				System.out.println("Neuspešno otvaranje datoteke!");
			} catch (IOException ex) {
				ex.printStackTrace();
				System.out.println("Neuspešno čitanje datoteke!");
			} catch (IndexOutOfBoundsException | NumberFormatException ex) { // multi-catch
				ex.printStackTrace();
				System.out.println("Oštećeni podaci u datoteci!");
			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("Nepredviđena greška!");
			}
		} while (Konzola.ocitajIzbor("Da li želite da ponovite?"));
	}

}
