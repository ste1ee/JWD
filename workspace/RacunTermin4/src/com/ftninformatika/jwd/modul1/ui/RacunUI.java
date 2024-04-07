package com.ftninformatika.jwd.modul1.ui;

import java.io.IOException;

import java.time.LocalDateTime;
import java.util.Map;

import com.ftninformatika.jwd.modul1.uti.model.Prodavnica;
import com.ftninformatika.jwd.modul1.uti.model.Racun;
import com.ftninformatika.jwd.modul1.uti.model.Stavka;
import com.ftninformatika.jwd.modul1.util.Konzola;
import com.ftninformatika.jwd.modul1.util.Meni;
import com.ftninformatika.jwd.modul1.util.Meni.FunkcionalnaStavkaMenija;
import com.ftninformatika.jwd.modul1.util.Meni.IzlaznaStavkaMenija;
import com.ftninformatika.jwd.modul1.util.Meni.StavkaMenija;

public class RacunUI {

	public static Racun pronalazenje() {
		prikazSvih();
		long id = Konzola.ocitajLong("Unesite ID racuna");
		Map<Long, Racun> racuni = Prodavnica.getRacuni();
		Racun racun = racuni.get(id);
		if (racun == null) {
			Konzola.prikazi("Nepostojeci racun!");
		}
		return racun;
	}

	static void prikazSvih() {
		Map<Long, Racun> racuni = Prodavnica.getRacuni();
		for (Racun r : racuni.values()) {
			System.out.println(r);
			;
		}
	}

	private static void prikaz() {
		Racun racun = pronalazenje();
		if (racun == null) {
			return;
		}
		System.out.println(racun + " " + " " + racun.getStavke());
	}

	private static void dodavanje() {
		long id = Prodavnica.nextRacunId();
		LocalDateTime datum = LocalDateTime.now();
		double ukupnaCena = 0.0;
		long idStavke = 0;
		Racun racun = new Racun(id, datum, ukupnaCena);
		Map<Long, Racun> racuni = Prodavnica.getRacuni();
		Map<Long, Stavka> stavke = Prodavnica.getStavke();
		if (idStavke == 0) {
			if (Konzola.ocitajIzbor("Da li zelis da dodas neku stavku?")) {
				StavkaUI.prikazSvih();
				idStavke = Konzola.ocitajLong("Unesi ID stavke");
				racun.addStavka(stavke.get(idStavke));
			}
		}
		racuni.put(racun.getId(), racun);

		try {
			Prodavnica.sacuvaj();
			Konzola.prikazi("Uspesno dodato!");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private static void izmena() {
		Racun racun = pronalazenje();
		if (racun == null) {
			return;
		}
		Map<Long, Stavka> stavke = Prodavnica.getStavke();
		long idStavke = 0;
		if (idStavke == 0) {
			if (Konzola.ocitajIzbor("Zelis li da dodas novu stavku na racun?")) {
				StavkaUI.prikazSvih();
				idStavke = Konzola.ocitajLong("Unesi ID nove stavke");
				racun.addStavka(stavke.get(idStavke));
			} else if (Konzola.ocitajIzbor("Zelis li da uklonis stavku sa racuna?")) {
				StavkaUI.prikazSvih();
				idStavke = Konzola.ocitajLong("Unesi ID stavke za uklanjanje");
				racun.removeStavka(stavke.get(idStavke));
			}
		}

		try {
			Prodavnica.sacuvaj();
			Konzola.prikazi("Uspesno izmenjeno!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void brisanje() {
		Racun racun = pronalazenje();
		if (racun == null) {
			return;
		}
		Map<Long, Racun> racuni = Prodavnica.getRacuni();
		racuni.remove(racun.getId());

		try {
			Prodavnica.sacuvaj();
			Konzola.prikazi("Uspesno izbrisano!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void meni() {
		Meni.pokreni("Racuni",
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
