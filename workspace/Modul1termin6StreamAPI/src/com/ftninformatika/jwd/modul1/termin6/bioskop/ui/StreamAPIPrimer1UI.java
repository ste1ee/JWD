package com.ftninformatika.jwd.modul1.termin6.bioskop.ui;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ftninformatika.jwd.modul1.termin6.bioskop.model.Bioskop;
import com.ftninformatika.jwd.modul1.termin6.bioskop.model.Film;

public class StreamAPIPrimer1UI {

	private static void prolazak(List<Film> filmovi) {
		System.out.println();
		for (Film itFilm : filmovi) {
			System.out.println(itFilm);
		}
	}

	public static void main(String[] args) throws Exception {
		Bioskop.ucitaj();

		List<Film> filmovi = new ArrayList<>(Bioskop.getFilmovi().values());
		prolazak(filmovi);

		// int trajanje = 100;
		// int trajanje = 150;
		String kljucnaRec = "It";
		List<Film> rezultat = filmovi.stream().filter(film -> film.getNaziv().contains(kljucnaRec))
				.collect(Collectors.toList());
		prolazak(rezultat);

		Film resenje2 = filmovi.stream().max(Comparator.comparingInt(film -> film.getNaziv().length())).get();
		System.out.println();
		System.out.println(resenje2);
		
		int brojZanrova = 3;
		long resenje3 = filmovi.stream().filter(film -> film.getZanrovi().size() > brojZanrova).count();
		System.out.println();
		System.out.println(resenje3);
		
		//long resenje4 = filmovi.stream().collect(Collectors.summingLong();
		

		/*
		 * Stream<Film> stream = filmovi.stream(); stream = stream.filter(film ->
		 * film.getTrajanje() < trajanje); Film rezultat =
		 * stream.max(Film::compareTrajanje).get();
		 */

		/*
		 * Film rezultat = filmovi.stream().filter(film -> film.getTrajanje() <
		 * trajanje).max(Film::compareTrajanje).get();
		 * 
		 * System.out.println(); System.out.println(rezultat);
		 */

		// Optional<Film> rezultat = filmovi.stream().filter(film -> film.getTrajanje()
		// < trajanje).max(Film::compareTrajanje);

		// System.out.println();
		// System.out.println(rezultat.isPresent()? rezultat.get(): "NIJE PRONAÄ�EN");
	}

}
