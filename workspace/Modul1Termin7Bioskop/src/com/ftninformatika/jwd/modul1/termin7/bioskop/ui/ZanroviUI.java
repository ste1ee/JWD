package com.ftninformatika.jwd.modul1.termin7.bioskop.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.ftninformatika.jwd.modul1.termin7.bioskop.dao.ZanrDAO;
import com.ftninformatika.jwd.modul1.termin7.bioskop.model.Zanr;
import com.ftninformatika.jwd.modul1.util.Konzola;
import com.ftninformatika.jwd.modul1.util.Meni;
import com.ftninformatika.jwd.modul1.util.Meni.FunkcionalnaStavkaMenija;
import com.ftninformatika.jwd.modul1.util.Meni.IzlaznaStavkaMenija;
import com.ftninformatika.jwd.modul1.util.Meni.StavkaMenija;
import com.ftninformatika.jwd.modul1.util.Tabela;

public class ZanroviUI {

	private static ZanrDAO zanrDAO;
	
	public static void setZanrDAO(ZanrDAO zanrDAO) {
		ZanroviUI.zanrDAO = zanrDAO;
	}

	private static final Tabela<Zanr> TABELA = new Tabela<>(
			"%2s %-20s",
			new Object[] {"ID", "Naziv"}
		) {

			@Override
			protected List<Object[]> uredi(Zanr vrednost) {
				List<Object[]> rezultat = new ArrayList<>();
				rezultat.add(new Object[] { vrednost.getId(), vrednost.getNaziv() });
				return rezultat;
			}

	};

	public static Zanr pronalazenje() throws Exception {
		prikazSvih();

		long id = Konzola.ocitajLong("Unesite ID žanra");

		Zanr zanr = zanrDAO.get(id);
		if (zanr == null)
			Konzola.prikazi("Žanr nije pronađen!");

		return zanr;
	}
	
	private static void prikazSvih() {
		try {
			Collection<Zanr> zanrovi = zanrDAO.getAll();
			TABELA.prikazi(zanrovi);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Došlo je do greške!");
		}
	}

	private static void prikaz() {
		try {
			Zanr zanr = pronalazenje();
			if (zanr == null)
				return;
			
			TABELA.prikazi(zanr);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Došlo je do greške!");
		}
	}

	private static void dodavanje() {
		String naziv = "";
		while (naziv.equals(""))
			naziv = Konzola.ocitajString("Unesite naziv");

		Zanr zanr = new Zanr(naziv);
		try {
			// čuvanje
			zanrDAO.add(zanr);
			Konzola.prikazi("Uspešno dodavanje!");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Došlo je do greške!");
		}
	}

	private static void izmena() {
		try {
			// pronalaženje postojećeg
			Zanr zanr = pronalazenje();
			if (zanr == null)
				return;

			// izmena
			String naziv = "";
			while (naziv.equals(""))
				naziv = Konzola.ocitajString("Unesite naziv");
			zanr.setNaziv(naziv);

			// čuvanje
			zanrDAO.update(zanr);
			Konzola.prikazi("Uspešna izmena!");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Došlo je do greške!");
		}
	}

	private static void brisanje() {
		try {
			// pronalaženje postojećeg
			Zanr zanr = pronalazenje();
			if (zanr == null)
				return;

			// čuvanje
			zanrDAO.delete(zanr.getId());
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Došlo je do greške!");
		}
	}
	
	public static void meni() {
		Meni.pokreni("Zanrovi", new StavkaMenija[] {
			new IzlaznaStavkaMenija("Povratak"),
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
