package com.ftninformatika.jwd.modul1.termin7.bioskop.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.ftninformatika.jwd.modul1.termin7.bioskop.dao.FilmDAO;
import com.ftninformatika.jwd.modul1.termin7.bioskop.model.Film;
import com.ftninformatika.jwd.modul1.termin7.bioskop.model.Zanr;
import com.ftninformatika.jwd.modul1.util.Konzola;
import com.ftninformatika.jwd.modul1.util.Meni;
import com.ftninformatika.jwd.modul1.util.Tabela;
import com.ftninformatika.jwd.modul1.util.Meni.FunkcionalnaStavkaMenija;
import com.ftninformatika.jwd.modul1.util.Meni.IzlaznaStavkaMenija;
import com.ftninformatika.jwd.modul1.util.Meni.StavkaMenija;

public class FilmoviUI {

	private static FilmDAO filmDAO;
	
	public static void setFilmDAO(FilmDAO filmDAO) {
		FilmoviUI.filmDAO = filmDAO;
	}

	private static final Tabela<Film> TABELA = new Tabela<>(
			"%2s %-50s %-10s %-20s",
			new Object[] {"ID", "Naziv", "Trajanje", "Žanrovi"}
		) {

			@Override
			protected List<Object[]> uredi(Film vrednost) {
				List<Object[]> rezultat = new ArrayList<>();
				rezultat.add(new Object[] { vrednost.getId(), vrednost.getNaziv(), vrednost.getTrajanje(), "" });
				for (Zanr itZanr: vrednost.getZanrovi()) // many-to-many veza
					rezultat.add(new Object[] { "", "", "", itZanr.getNaziv() });
				return rezultat;
			}
	
	};
	
	public static Film pronalazenje() throws Exception {
		prikazSvih();

		long id = Konzola.ocitajLong("Unesite ID filma");

		Film film = filmDAO.get(id);
		if (film == null)
			Konzola.prikazi("Film nije pronađen!");

		return film;
	}

	private static void prikazSvih() {
		try {
			Collection<Film> filmovi = filmDAO.getAll();
			TABELA.prikazi(filmovi);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Došlo je do greške!");
		}
	}

	private static void prikaz() {
		try {
			Film film = pronalazenje();
			if (film == null)
				return;
			
			TABELA.prikazi(film);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Došlo je do greške!");
		}
	}

	private static void dodavanje() {
		String naziv = "";
		while (naziv.equals(""))
			naziv = Konzola.ocitajString("Unesite naziv");

		int trajanje = 0;
		while (trajanje <= 5)
			trajanje = Konzola.ocitajInt("Unesite trajanje");

		Film film = new Film(naziv, trajanje);		
		try {
			// čuvanje
			filmDAO.add(film);
			Konzola.prikazi("Uspešno dodavanje!");	
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Došlo je do greške!");
		}
	}

	private static void izmena() {
		try {
			// pronalaženje postojećeg
			Film film = pronalazenje();
			if (film == null)
				return;

			// izmena
			String naziv = "";
			while (naziv == "")
				naziv = Konzola.ocitajString("Unesite naziv");
			film.setNaziv(naziv);

			int trajanje = 0;
			while (trajanje <= 5)
				trajanje = Konzola.ocitajInt("Unesite trajanje");
			film.setTrajanje(trajanje);

			// čuvanje
			filmDAO.update(film);
			Konzola.prikazi("Uspešna izmena!");		
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Došlo je do greške!");
		}

	}

	private static void brisanje() {
		try {
			// pronalaženje postojećeg
			Film film = pronalazenje();
			if (film == null)
				return;

			// čuvanje
			filmDAO.delete(film.getId());
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Došlo je do greške!");
		}
	}
	
	public static void meni() {
		Meni.pokreni("Filmovi", new StavkaMenija[] {
			new IzlaznaStavkaMenija("Porvratak"),
			new FunkcionalnaStavkaMenija("Prikaz svih") {

				@Override
				public void izvrsi() { prikazSvih(); }

			},
			new FunkcionalnaStavkaMenija("Prikaz") {

				@Override
				public void izvrsi() { prikaz(); }

			},
			new FunkcionalnaStavkaMenija("Dodavanje") {

				@Override
				public void izvrsi() { dodavanje(); }

			},
			new FunkcionalnaStavkaMenija("Izmena") {

				@Override
				public void izvrsi() { izmena(); }

			},
			new FunkcionalnaStavkaMenija("Brisanje") {

				@Override
				public void izvrsi() { brisanje(); }

			}
		});
	}
	
}
