package com.ftninformatika.jwd.modul1.termin6.bioskop.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

import com.ftninformatika.jwd.modul1.termin6.bioskop.model.Bioskop;
import com.ftninformatika.jwd.modul1.termin6.bioskop.model.Film;

public class StreamAPIPrimer5UI {

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
		/*
		OptionalDouble rezultat = filmovi.stream().mapToInt(Film::getTrajanje).average();

		System.out.println();
		System.out.println(rezultat.isPresent()? rezultat.getAsDouble(): 0.0);
		*/
		
		double rezultat = filmovi.stream().mapToInt(Film::getTrajanje).average().orElse(0.0);

		System.out.println();
		System.out.println(rezultat);
	}

}
