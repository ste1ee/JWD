package com.ftninformatika.jwd.modul1.termin6.bioskop.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.ftninformatika.jwd.modul1.termin6.bioskop.model.Bioskop;
import com.ftninformatika.jwd.modul1.termin6.bioskop.model.Film;

public class MapUI {

	private static void prolazakKljuceva(Map<Long, Film> filmovi) {
		for (Long itID: filmovi.keySet()) {
			System.out.println(itID);
		}
	}
	
	private static void prolazakVrednosti(Map<Long, Film> filmovi) {
		for (Film itFilm: filmovi.values()) {
			System.out.println(itFilm);
		}
	}

	private static void prolazakParova(Map<Long, Film> filmovi) {
		for (Entry<Long, Film> itPar: filmovi.entrySet()) {
			Long id = itPar.getKey();
			Film film = itPar.getValue();
			System.out.println(String.format("%s -> %s", id, film));
		}
	}
	
	private static void prolazak(Map<Long, Film> filmovi) {
		//prolazakKljuceva(filmovi);
		prolazakVrednosti(filmovi);
		//prolazakParova(filmovi);
	}

	private static void dodavanje(Map<Long, Film> filmovi) {
		Film film1 = Bioskop.getFilmovi().get(1L);
		Film film2 = Bioskop.getFilmovi().get(2L);
		Film film3 = Bioskop.getFilmovi().get(3L);
		Film film4 = Bioskop.getFilmovi().get(4L);
		filmovi.put(film1.getId(), film1);
		filmovi.put(film2.getId(), film2);
		filmovi.put(film3.getId(), film3);
		filmovi.put(film4.getId(), film4);
		
		Film duplikat = new Film(1L, "DUPLIKAT", 0);
		// prepisuje element ako se javi duplikat ključa
		// proverava se podudarnost ključa po hashCode() i equals(...) metodama
		filmovi.put(duplikat.getId(), duplikat);

		System.out.println();
		System.out.println("DODAVANJE");
		prolazak(filmovi);
	}

	private static void proveraPostojanjaKljuca(Map<Long, Film> filmovi) {
		long id = 1L;

		boolean postoji = filmovi.containsKey(id);
		
		System.out.println();
		System.out.println("PROVERA POSTOJANJA KLJUČA");
		System.out.println(String.format("%s %s", id, postoji? "POSTOJI": "NE POSTOJI"));
	}
	
	private static void izvlacenje(Map<Long, Film> filmovi) {
		long id = 1L;
		// proverava se podudarnost ključa po hashCode() i equals(...) metodama
		Film pronadjeniFilm = filmovi.get(id);
		if (pronadjeniFilm != null) {
			System.out.println();
			System.out.println("IZVLAČENJE");
			System.out.println(pronadjeniFilm);
		}
	}
	
	private static void uklanjanje(Map<Long, Film> filmovi) {
		// UKLANJANJE REMOVE METODOM KROZ MAPU NIJE U SVIM IMPLEMENTACIJAMA BEZBEDNO ZA VREME PROLASKA
		// UKOLIKO SE PROLAZAK OBAVLJA IMPLICITNIM ITERATOROM
		long id = 1L;
		// proverava se podudarnost ključa po hashCode() i equals(...) metodama
		filmovi.remove(id);
		
		System.out.println();
		System.out.println("UKLANJANJE");
		prolazak(filmovi);
	}

	private static void sortiranje(Map<Long, Film> filmovi) {
		// Mapu je nemoguće sortirati, pa se kreira privremena lista
		List<Film> kopija = new ArrayList<>(filmovi.values());
		
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

	private static void neizmenljivaKopija(Map<Long, Film> filmovi) {
		Map<Long, Film> kopija = Collections.unmodifiableMap(filmovi); // izmene na originalu će biti vidljive u kopiji
		//Map<Long, Film> kopija = Map.copyOf(filmovi); // izmene na originalu neće biti vidljive u kopiji

		System.out.println();
		System.out.println("NEIZMENLJIVA KOPIJA");
		prolazak(kopija);
	}
	
	public static void main(String[] args) throws Exception {
		Bioskop.ucitaj();

		Map<Long, Film> filmovi = new HashMap<>(Bioskop.getFilmovi()); // copy constructor
		//Map<Long, Film> filmovi = new LinkedHashMap<>(Bioskop.getFilmovi()); // copy constructor

		dodavanje(filmovi);
		proveraPostojanjaKljuca(filmovi);
		izvlacenje(filmovi);
		uklanjanje(filmovi);
		sortiranje(filmovi);
		neizmenljivaKopija(filmovi);
	}

}
