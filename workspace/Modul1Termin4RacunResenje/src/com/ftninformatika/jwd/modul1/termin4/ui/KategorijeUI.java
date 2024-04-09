package com.ftninformatika.jwd.modul1.termin4.ui;

import java.util.Map;
import java.util.Scanner;

import com.ftninformatika.jwd.modul1.termin4.model.Kategorija;
import com.ftninformatika.jwd.modul1.termin4.model.Prodavnica;
import com.ftninformatika.jwd.modul1.termin4.util.Konzola;
import com.ftninformatika.jwd.modul1.termin4.util.Meni;
import com.ftninformatika.jwd.modul1.termin4.util.Meni.FunkcionalnaStavkaMenija;
import com.ftninformatika.jwd.modul1.termin4.util.Meni.IzlaznaStavkaMenija;
import com.ftninformatika.jwd.modul1.termin4.util.Meni.StavkaMenija;

public class KategorijeUI {

	public static Kategorija pronalazenje() {
		prikazSvih();

		long id = Konzola.ocitajLong("Unesite ID kategorije");

		Map<Long, Kategorija> kategorije = Prodavnica.getKategorije();
		Kategorija kategorija = kategorije.get(id);
		if (kategorija == null)
			Konzola.prikazi("Kategorija nije pronaÄ‘ena!");
		return kategorija;
	}

	static void prikazSvih() {
		Map<Long, Kategorija> kategorije = Prodavnica.getKategorije();

		for (Kategorija k : kategorije.values()) {
			System.out.println(k);
		}
	}

	private static void prikaz() {

		Kategorija kategorija = pronalazenje();
		if (kategorija == null)
			return;

		System.out.println(kategorija);

	}

	private static void dodavanje() {
		// kreiranje
		long id = Prodavnica.nextKategorijaId();

		String naziv = "";
		while (naziv.equals(""))
			naziv = Konzola.ocitajString("Unesite naziv:");

		Kategorija kategorija = new Kategorija(id, naziv);

		// dodavanje u mapi
		Map<Long, Kategorija> kategorije = Prodavnica.getKategorije();
		kategorije.put(kategorija.getId(), kategorija);
		// Ä�uvanje u fajl
		try {
			Prodavnica.sacuvaj();
			Konzola.prikazi("UspeÅ¡no dodavanje!");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void izmena() {
		// pronalaÅ¾enje postojeÄ‡eg
		Kategorija kategorija = pronalazenje();
		if (kategorija == null)
			return;

		// izmena
		String naziv = "";
		while (naziv.equals(""))
			naziv = Konzola.ocitajString("Unesite naziv");
		kategorija.setNaziv(naziv);
		// Ä�uvanje
		try {
			Prodavnica.sacuvaj();
			Konzola.prikazi("UspeÅ¡na izmena!");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void brisanje() {
		// pronalaÅ¾enje postojeÄ‡eg
		Kategorija kategorija = pronalazenje();
		if (kategorija == null)
			return;

		// brisanje
		Map<Long, Kategorija> kategorije = Prodavnica.getKategorije();
		kategorije.remove(kategorija.getId());
		// Ä�uvanje
		try {
			Prodavnica.sacuvaj();
			Konzola.prikazi("UspeÅ¡no brisanje!");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void meniAdmin() {
		Meni.pokreni("Kategorije",
				new StavkaMenija[] { new IzlaznaStavkaMenija("Povratak"), new FunkcionalnaStavkaMenija("Prikaz svih") {

					@Override
					public void izvrsi() {
						prikazSvih();
					}

				}, new FunkcionalnaStavkaMenija("Prikaz") {

					@Override
					public void izvrsi() {
						prikaz();
					}

				}, new FunkcionalnaStavkaMenija("Dodavanje") {

					@Override
					public void izvrsi() {
						dodavanje();
					}

				}, new FunkcionalnaStavkaMenija("Izmena") {

					@Override
					public void izvrsi() {
						izmena();
					}

				}, new FunkcionalnaStavkaMenija("Brisanje") {

					@Override
					public void izvrsi() {
						brisanje();
					}

				} });
	}

	public static void meniRadnik() {
		Meni.pokreni("Kategorije",
				new StavkaMenija[] { new IzlaznaStavkaMenija("Povratak"), new FunkcionalnaStavkaMenija("Prikaz svih") {

					@Override
					public void izvrsi() {
						prikazSvih();
					}

				}, new FunkcionalnaStavkaMenija("Prikaz") {

					@Override
					public void izvrsi() {
						prikaz();
					}

				} });
	}

}
