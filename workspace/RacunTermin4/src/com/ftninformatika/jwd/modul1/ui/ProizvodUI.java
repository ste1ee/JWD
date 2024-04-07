package com.ftninformatika.jwd.modul1.ui;

import java.io.IOException;
import java.util.Map;

import com.ftninformatika.jwd.modul1.uti.model.Prodavnica;
import com.ftninformatika.jwd.modul1.uti.model.Proizvod;
import com.ftninformatika.jwd.modul1.util.Konzola;
import com.ftninformatika.jwd.modul1.util.Meni;
import com.ftninformatika.jwd.modul1.util.Meni.FunkcionalnaStavkaMenija;
import com.ftninformatika.jwd.modul1.util.Meni.IzlaznaStavkaMenija;
import com.ftninformatika.jwd.modul1.util.Meni.StavkaMenija;

public class ProizvodUI {

	public static Proizvod pronalazenje() {
		prikazSvih();
		long id = Konzola.ocitajLong("Unesite ID proizvoda");
		Map<Long, Proizvod> proizvodi = Prodavnica.getProizvodi();
		Proizvod proizvod = proizvodi.get(id);
		if (proizvod == null) {
			Konzola.prikazi("Nepostojeci proizvod");
		}
		return proizvod;
	}

	static void prikazSvih() {
		Map<Long, Proizvod> proizvodi = Prodavnica.getProizvodi();
		for (Proizvod p : proizvodi.values()) {
			System.out.println(p);
		}
	}

	private static void prikaz() {
		Proizvod proizvod = pronalazenje();
		if (proizvod == null) {
			return;
		}
		System.out.println(proizvod);
	}

	private static void dodavanje() {
		long id = Prodavnica.nextKategorijaId();
		String naziv = "";
		double cena = 0.0;
		while (naziv.equals("")) {
			naziv = Konzola.ocitajString("Unesi naziv");
		}
		while (cena == 0.0) {
			cena = Konzola.ocitajDouble("Unesi cenu");
		}
		Proizvod proizvod = new Proizvod(id, naziv, cena);
		Map<Long, Proizvod> proizvodi = Prodavnica.getProizvodi();
		proizvodi.put(proizvod.getId(), proizvod);

		try {
			Prodavnica.sacuvaj();
			Konzola.prikazi("Uspesno dodato");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void izmena() {
		Proizvod proizvod = pronalazenje();

		if (proizvod == null) {
			return;
		}
		String naziv = "";
		double cena = 0.0;
		while (naziv.equals("")) {
			naziv = Konzola.ocitajString("Unesi naziv");
		}
		while (cena == 0.0) {
			cena = Konzola.ocitajDouble("Unesi cenu");
		}
		proizvod.setNaziv(naziv);
		proizvod.setCena(cena);

		try {
			Prodavnica.sacuvaj();
			Konzola.prikazi("Uspesno izmenjeno");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void brisanje() {
		Proizvod proizvod = pronalazenje();
		if (proizvod == null) {
			return;
		}
		Map<Long, Proizvod> proizvodi = Prodavnica.getProizvodi();
		proizvodi.remove(proizvod.getId());

		try {
			Prodavnica.sacuvaj();
			Konzola.prikazi("Uspesno izbrisano!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void meni() {
		Meni.pokreni("Proizvodi",
				new StavkaMenija[] { new IzlaznaStavkaMenija("Povratak"), new FunkcionalnaStavkaMenija("Prikazi sve") {
					@Override
					public void izvrsi() {
						prikazSvih();
					}
				}, new FunkcionalnaStavkaMenija("Prikazi") {
					@Override
					public void izvrsi() {
						prikaz();
					}
				}, new FunkcionalnaStavkaMenija("Dodaj") {

					@Override
					public void izvrsi() {
						dodavanje();
					}
				}, new FunkcionalnaStavkaMenija("Izmeni") {

					@Override
					public void izvrsi() {
						izmena();

					}
				}, new FunkcionalnaStavkaMenija("Izbrisi") {

					@Override
					public void izvrsi() {
						brisanje();

					}
				} });

	}

}
