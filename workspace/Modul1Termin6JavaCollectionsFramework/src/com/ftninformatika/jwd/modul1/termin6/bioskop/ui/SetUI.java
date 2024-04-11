package com.ftninformatika.jwd.modul1.termin6.bioskop.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.ftninformatika.jwd.modul1.termin6.bioskop.model.Bioskop;
import com.ftninformatika.jwd.modul1.termin6.bioskop.model.Film;

public class SetUI {
	
	private static void prolazak(Set<Film> filmovi) {
		for (Film itFilm: filmovi) {
			System.out.println(itFilm);
		}
	}

	private static void dodavanje(Set<Film> filmovi) {
		Film duplikat = Bioskop.getFilmovi().get(1L);
		// sprečava dodavanje duplikata
		// proverava se podudarnost po hashCode() i equals(...) metodama
		filmovi.add(duplikat);

		Film film = new Film(5L, "DODAVANJE", 0);
		filmovi.add(film);

		System.out.println();
		System.out.println("DODAVANJE");
		prolazak(filmovi);
	}

	private static void uklanjanje(Set<Film> filmovi) {
		// UKLANJANJE REMOVE METODOM KROZ KOLEKCIJU NIJE U SVIM IMPLEMENTACIJAMA BEZBEDNO ZA VREME PROLASKA
		// UKOLIKO SE PROLAZAK OBAVLJA IMPLICITNIM ITERATOROM
		Film element = Bioskop.getFilmovi().get(2L);
		// uklanjanje po vrednosti
		// mora i van Set-a postojati referenca na element koji se uklanja
		// proverava se podudarnost po hashCode() i equals(...) metodama
		filmovi.remove(element);
		
		System.out.println();
		System.out.println("UKLANJANJE");
		prolazak(filmovi);
	}

	private static void sortiranje(Set<Film> filmovi) {
		// Set je nemoguće sortirati, pa se kreira privremena lista
		List<Film> kopija = new ArrayList<>(filmovi);
		
		Comparator<Film> comp = new Comparator<Film>() {
			@Override
			public int compare(Film film1, Film film2) {
				return film1.getNaziv().compareTo(film2.getNaziv());
				//return Integer.compare(film1.getTrajanje(), film2.getTrajanje()); // poređenje po primitivnom tipu
			}		
		};		
		kopija.sort(comp);
		//Collections.sort(kopija, comp); // 2. način
		//kopija.sort(comp.reversed()); // sortiranje u obrnutom redosledu

		System.out.println();
		System.out.println("SORTIRANJE");
		for (Film itFilm: kopija) {
			System.out.println(itFilm);
		}
	}

	private static void neizmenljivaKopija(Set<Film> filmovi) {
		Set<Film> kopija = Collections.unmodifiableSet(filmovi); // izmene na originalu će biti vidljive u kopiji
		//Set<Film> kopija = Set.copyOf(filmovi); // izmene na originalu neće biti vidljive u kopiji

		System.out.println();
		System.out.println("NEIZMENLJIVA KOPIJA");
		prolazak(kopija);
	}
	
	public static void main(String[] args) throws Exception {
		Bioskop.ucitaj();
		
		Set<Film> filmovi = new HashSet<>(Bioskop.getFilmovi().values()); // copy constructor
		//Set<Film> filmovi = new LinkedHashSet<>(Bioskop.getFilmovi().values()); // copy constructor
		//Set<Film> filmovi = new HashSet<>();
		//Set<Film> filmovi = new LinkedHashSet<>();

		dodavanje(filmovi);
		uklanjanje(filmovi);
		sortiranje(filmovi);
		neizmenljivaKopija(filmovi);
	}

}
