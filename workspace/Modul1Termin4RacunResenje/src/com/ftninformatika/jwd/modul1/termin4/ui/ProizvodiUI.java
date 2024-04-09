package com.ftninformatika.jwd.modul1.termin4.ui;

import java.util.Map;

import com.ftninformatika.jwd.modul1.termin4.model.Kategorija;
import com.ftninformatika.jwd.modul1.termin4.model.Prodavnica;
import com.ftninformatika.jwd.modul1.termin4.model.Proizvod;
import com.ftninformatika.jwd.modul1.termin4.util.Konzola;
import com.ftninformatika.jwd.modul1.termin4.util.Meni;
import com.ftninformatika.jwd.modul1.termin4.util.Meni.FunkcionalnaStavkaMenija;
import com.ftninformatika.jwd.modul1.termin4.util.Meni.IzlaznaStavkaMenija;
import com.ftninformatika.jwd.modul1.termin4.util.Meni.StavkaMenija;

public class ProizvodiUI {

	public static Proizvod pronalazenje() {
		prikazSvih();

		long id = Konzola.ocitajLong("Unesite ID proizvoda");

		Map<Long, Proizvod> proizvodi = Prodavnica.getProizvod();
		Proizvod proizvod = proizvodi.get(id);
		if (proizvod == null)
			Konzola.prikazi("Proizvod nije pronaÄ‘en!");

		return proizvod;
	}

	static void prikazSvih() {
		Map<Long, Proizvod> proizvodi = Prodavnica.getProizvod();
		for (Proizvod p : proizvodi.values()) {
			System.out.println(p);
		}
	}

	private static void prikaz() {

		Proizvod proizvod = pronalazenje();
		if (proizvod == null)
			return;

		System.out.println(proizvod);
	}

	private static void dodavanje() {
		// kreiranje
		long id = Prodavnica.nextProizvodId();

		String naziv = "";
		while (naziv.equals(""))
			naziv = Konzola.ocitajString("Unesite naziv");
		double cena = 0.0;
		while (cena == 0.0)
			cena = Konzola.ocitajDouble("Unesite cenu");

		Proizvod proizvod = new Proizvod(id, naziv, cena);

		// dodati kategoriju
		KategorijeUI.prikazSvih();
		long kategorijaId = Konzola.ocitajLong("Unesite ID kategorije");

		Map<Long, Kategorija> kategorije = Prodavnica.getKategorije();
		Kategorija kategorija = kategorije.get(kategorijaId);
		proizvod.addKategoriju(kategorija);

		// dodavanje proizvoda u mapi
		Map<Long, Proizvod> proizvodi = Prodavnica.getProizvod();
		proizvodi.put(proizvod.getId(), proizvod);
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
		Proizvod proizvod = pronalazenje();
		if (proizvod == null)
			return;

		// izmena
		String naziv = "";
		while (naziv.equals(""))
			naziv = Konzola.ocitajString("Unesite naziv");
		proizvod.setNaziv(naziv);
		double cena = 0.0;
		while (cena == 0.0)
			cena = Konzola.ocitajDouble("Unesite cenu");

		proizvod.setCena(cena);

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
		Proizvod proizvod = pronalazenje();
		if (proizvod == null)
			return;

		// razvezivanje
		proizvod.removeAllKategorije();
		// brisanje iz mape
		Map<Long, Proizvod> proizvodi = Prodavnica.getProizvod();
		proizvodi.remove(proizvod.getId());
		// Ä�uvanje u fajl
		try {
			Prodavnica.sacuvaj();
			Konzola.prikazi("UspeÅ¡no brisanje!");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void meniAdmin() {
		Meni.pokreni("Proizvodi",
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

				},

						new FunkcionalnaStavkaMenija("Brisanje") {

							@Override
							public void izvrsi() {
								brisanje();
							}

						} });
	}

	public static void meniRadnik() {
		Meni.pokreni("Proizvodi",
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
