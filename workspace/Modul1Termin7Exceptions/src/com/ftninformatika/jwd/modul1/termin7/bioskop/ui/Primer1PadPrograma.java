package com.ftninformatika.jwd.modul1.termin7.bioskop.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

import com.ftninformatika.jwd.modul1.termin7.bioskop.model.Film;
import com.ftninformatika.jwd.modul1.util.Konzola;

public class Primer1PadPrograma {

	private static final File ISPRAVNA = Paths.get("data", "filmovi.csv").toFile();
	private static final File NEPOSTOJECA = Paths.get("data", "filmovi_nepostojeci.csv").toFile();
	private static final File OSTECENA = Paths.get("data", "filmovi_osteceni.csv").toFile();
	
	private static void prolazak(Collection<Film> filmovi) {
		System.out.println();
		for (Film itFilm: filmovi) {
			System.out.println(itFilm);
		}
	}

	public static void main(String[] args) throws Exception {
		do {	
			Collection<Film> filmovi = new ArrayList<>();
	
			BufferedReader in = new BufferedReader(new FileReader(ISPRAVNA)); // ova linija može da proizvede Exception

			String line = in.readLine(); // ova linija može da proizvede Exception
			while (line != null) {
				String[] tokeni = line.split(",");
				long id = Long.parseLong(tokeni[0]); // ova linija može da proizvede Exception
				String naziv = tokeni[1]; // ova linija može da proizvede Exception
				int trajanje = Integer.parseInt(tokeni[2]); // ova linija može da proizvede Exception
				
				Film film = new Film(id, naziv, trajanje);
				filmovi.add(film);
				
				line = in.readLine(); // ova linija može da proizvede Exception
			}
			in.close();

			prolazak(filmovi);		
		} while (Konzola.ocitajIzbor("Da li želite da ponovite?"));
	}

}
