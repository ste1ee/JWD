package com.ftninformatika.jwd.modul1.termin6.bioskop.ui;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.ftninformatika.jwd.modul1.termin6.bioskop.model.Bioskop;
import com.ftninformatika.jwd.modul1.termin6.bioskop.model.Film;

public class FunkcionalniInterfejsUI {

	private static void prolazak(List<Film> filmovi) {
		System.out.println();
		for (Film itFilm: filmovi) {
			System.out.println(itFilm);
		}
	}
	
	public static void main(String[] args) throws Exception {
		Bioskop.ucitaj();
		
		List<Film> filmovi = new ArrayList<>(Bioskop.getFilmovi().values());

		/*
		Comparator<Film> comp = new Comparator<Film>() { // 1. način: instanca funkcionalnog interfejsa
			@Override
			public int compare(Film film1, Film film2) {
				return film1.getNaziv().compareTo(film2.getNaziv());
			}		
		};
		filmovi.sort(comp);
		*/

		/*
		Comparator<Film> comp = (film1, film2) -> film1.getNaziv().compareTo(film2.getNaziv()); // 2. način: lambda izraz
		filmovi.sort(comp);
		*/

		/*
		filmovi.sort((film1, film2) -> film1.getNaziv().compareTo(film2.getNaziv())); // 3. način: lambda izraz (inline)
		*/

		filmovi.sort(Film::compareNaziv); // 4. način: referenca na funkciju

		prolazak(filmovi);
	}

}
