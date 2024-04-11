package com.ftninformatika.jwd.modul1.termin6.bioskop.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.ftninformatika.jwd.modul1.termin6.bioskop.model.Bioskop;
import com.ftninformatika.jwd.modul1.termin6.bioskop.model.Film;

public class ListUI {

	private static void prolazakVrednosti(List<Film> filmovi) {
		for (Film itFilm: filmovi) {
			System.out.println(itFilm);
		}
	}
	
	private static void prolazakPozicija(List<Film> filmovi) {
		for (int it = 0; it < filmovi.size(); it++) {
			Film film = filmovi.get(it);
			System.out.println(String.format("%s. %s", it + 1, film));
		}
	}
	
	private static void prolazak(List<Film> filmovi) {
		prolazakVrednosti(filmovi);
		//prolazakPozicija(filmovi);
	}

	private static void dodavanje(List<Film> filmovi) {
		Film film = new Film(5L, "DODAVANJE", 0);
		filmovi.add(film);

		System.out.println();
		System.out.println("DODAVANJE");
		prolazak(filmovi);
	}

	private static void izvlacenje(List<Film> filmovi) {
		int pozicija = 1;

		Film pronadjeniFilm = filmovi.get(pozicija);
		if (pronadjeniFilm != null) {
			System.out.println();
			System.out.println("IZVLAČENJE");
			System.out.println(pronadjeniFilm);
		}
	}
	
	private static void umetanje(List<Film> filmovi) {
		int pozicija = 1;

		Film film = new Film(6L, "UMETANJE", 0);
		filmovi.add(pozicija, film);
		
		System.out.println();
		System.out.println("UMETANJE");
		prolazak(filmovi);
	}

	private static void zamena(List<Film> filmovi) {
		int pozicija = 1;

		Film film = new Film(7L, "ZAMENA", 0);
		filmovi.set(pozicija, film);
		
		System.out.println();
		System.out.println("ZAMENA");
		prolazak(filmovi);
	}

	private static void uklanjanje(List<Film> filmovi) {
		// UKLANJANJE REMOVE METODOM KROZ KOLEKCIJU NIJE U SVIM IMPLEMENTACIJAMA BEZBEDNO ZA VREME PROLASKA
		// UKOLIKO SE PROLAZAK OBAVLJA IMPLICITNIM ITERATOROM
		int pozicija = 1;

		filmovi.remove(pozicija);
		
		System.out.println();
		System.out.println("UKLANJANJE");
		prolazak(filmovi);
	}

	private static void sortiranje(List<Film> filmovi) {
		Comparator<Film> comp = new Comparator<Film>() {
			@Override
			public int compare(Film film1, Film film2) {
				return film1.getNaziv().compareTo(film2.getNaziv());
				//return Integer.compare(film1.getTrajanje(), film2.getTrajanje()); // poređenje po primitivnom tipu
			}		
		};
		filmovi.sort(comp);
		//Collections.sort(filmovi, comp); // 2. način
		//filmovi.sort(comp.reversed()); // sortiranje u obrnutom redosledu

		System.out.println();
		System.out.println("SORTIRANJE");
		prolazak(filmovi);
	}

	private static void neizmenljivaKopija(List<Film> filmovi) {
		List<Film> kopija = Collections.unmodifiableList(filmovi); // izmene na originalu će biti vidljive u kopiji
		//List<Film> kopija = List.copyOf(filmovi); // izmene na originalu neće biti vidljive u kopiji

		System.out.println();
		System.out.println("NEIZMENLJIVA KOPIJA");
		prolazak(kopija);
	}
	
	public static void main(String[] args) throws Exception {
		Bioskop.ucitaj();
		
		List<Film> filmovi = new ArrayList<>(Bioskop.getFilmovi().values()); // copy constructor
		//List<Film> filmovi = new LinkedList<>(Bioskop.getFilmovi().values()); // copy constructor
		//List<Film> filmovi = new ArrayList<>();
		//List<Film> filmovi = new LinkedList<>();

		dodavanje(filmovi);
		izvlacenje(filmovi);
		umetanje(filmovi);
		zamena(filmovi);
		uklanjanje(filmovi);
		sortiranje(filmovi);
		neizmenljivaKopija(filmovi);
	}

}
