package com.ftninformatika.jwd.modul1.termin6.bioskop.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Collection;

import com.ftninformatika.jwd.modul1.termin6.bioskop.model.Bioskop;
import com.ftninformatika.jwd.modul1.termin6.bioskop.model.Film;

public class CollectionUI {

	private static void prolazakImplicitniIterator(Collection<Film> filmovi) {
		for (Film itFilm: filmovi) {
			System.out.println(itFilm);
		}
	}

	private static void prolazakEksplicitniIterator(Collection<Film> filmovi) {
		Iterator<Film> it = filmovi.iterator();
		while (it.hasNext()) {
			Film film = it.next();
			System.out.println(film);
		}
	}

	private static void prolazak(Collection<Film> filmovi) {
		prolazakImplicitniIterator(filmovi);
		//prolazakEksplicitniIterator(filmovi);
	}
	
	private static void dodavanje(Collection<Film> filmovi) {
		Film film = new Film(5L, "DODAVANJE", 0);
		filmovi.add(film);

		System.out.println();
		System.out.println("DODAVANJE");
		prolazak(filmovi);
	}

	private static void proveraPostojanja(Collection<Film> filmovi) {
		Film element = Bioskop.getFilmovi().get(2L);

		boolean postoji = filmovi.contains(element);
		
		System.out.println();
		System.out.println("PROVERA POSTOJANJA");
		System.out.println(String.format("%s %s", element, postoji? "POSTOJI": "NE POSTOJI"));
	}
	
	private static void izvlacenje(Collection<Film> filmovi) {
		long id = 2L;

		Film pronadjeniFilm = null;

		Iterator<Film> it = filmovi.iterator();
		while (it.hasNext()) {
			Film film = it.next();
			if (film.getId() == id) {
				pronadjeniFilm = film;
				break;
			}
		}
		System.out.println();
		System.out.println("IZVLAČENJE");
		if (pronadjeniFilm != null) {
			System.out.println(pronadjeniFilm);
		}
	}
	
	private static void uklanjanjeKrozKolekciju(Collection<Film> filmovi) {
		// UKLANJANJE REMOVE METODOM KROZ KOLEKCIJU NIJE U SVIM IMPLEMENTACIJAMA BEZBEDNO ZA VREME PROLASKA
		// UKOLIKO SE PROLAZAK OBAVLJA IMPLICITNIM ITERATOROM
		Film element = Bioskop.getFilmovi().get(2L);
		// uklanjanje po vrednosti
		// mora i van Collection-a postojati referenca na element koji se uklanja
		// proverava se podudarnost po equals(...) metodi
		filmovi.remove(element);
		
		System.out.println();
		System.out.println("UKLANJANJE");
		prolazak(filmovi);
	}
	
	private static void uklanjanjeKrozInterator(Collection<Film> filmovi) {
		// UKLANJANJE REMOVE METODOM KROZ ITERATOR JE U SVIM IMPLEMENTACIJAMA BEZBEDNO ZA VREME PROLASKA
		// UKOLIKO SE PROLAZAK OBAVLJA ISTIM ITERATOROM
		long id = 2L;

		Iterator<Film> it = filmovi.iterator();
		while (it.hasNext()) {
			Film film = it.next();
			if (film.getId() == id) {
				it.remove();
				break;
			}
		}
	}

	private static void uklanjanje(Collection<Film> filmovi) {
		uklanjanjeKrozKolekciju(filmovi);
		//uklanjanjeKrozInterator(filmovi);
	}
	
	private static void sortiranje(Collection<Film> filmovi) {
		// Collection je nemoguće sortirati, pa se kreira privremena lista
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

	private static void neizmenljivaKopija(Collection<Film> filmovi) {
		Collection<Film> kopija = Collections.unmodifiableCollection(filmovi); // izmene na originalu će biti vidljive u kopiji

		System.out.println();
		System.out.println("NEIZMENLJIVA KOPIJA");
		prolazak(kopija);
	}
	
	public static void main(String[] args) throws Exception {
		Bioskop.ucitaj();	
		Collection<Film> filmovi = new ArrayList<>(Bioskop.getFilmovi().values()); // copy constructor
		//Collection<Film> filmovi = new HashSet<>(Bioskop.getFilmovi().values()); // copy constructor
		//Collection<Film> filmovi = new ArrayList<>();
		//Collection<Film> filmovi = new HashSet<>();

		// SVE KOLEKCIJE PODRŽAVAJU NAČINE IMPLEMENTACIJE IZ OVOG PROGRAMA
		dodavanje(filmovi);
		proveraPostojanja(filmovi);
		izvlacenje(filmovi);
		uklanjanje(filmovi);
		sortiranje(filmovi);
		neizmenljivaKopija(filmovi);
	}

}
