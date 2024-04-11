package com.ftninformatika.jwd.modul1.termin6.bioskop.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.ftninformatika.jwd.modul1.termin6.bioskop.model.Bioskop;
import com.ftninformatika.jwd.modul1.termin6.bioskop.model.Film;

public class StreamAPIPrimer1UI {

	private static void prolazak(List<Film> filmovi) {
		System.out.println();
		for (Film itFilm: filmovi) {
			System.out.println(itFilm);
		}
	}
	
	public static void main(String[] args) throws Exception {
		Bioskop.ucitaj();
		
		List<Film> filmovi = new ArrayList<>(Bioskop.getFilmovi().values());
		prolazak(filmovi);

		int trajanje = 100;
		//int trajanje = 150;

		/*
		Stream<Film> stream = filmovi.stream();
		stream = stream.filter(film -> film.getTrajanje() < trajanje);
		Film rezultat = stream.max(Film::compareTrajanje).get();
		*/

		/*
		Film rezultat = filmovi.stream().filter(film -> film.getTrajanje() < trajanje).max(Film::compareTrajanje).get();

		System.out.println();
		System.out.println(rezultat);
		*/

		Optional<Film> rezultat = filmovi.stream().filter(film -> film.getTrajanje() < trajanje).max(Film::compareTrajanje);

		System.out.println();
		System.out.println(rezultat.isPresent()? rezultat.get(): "NIJE PRONAĐEN");
	}

}
