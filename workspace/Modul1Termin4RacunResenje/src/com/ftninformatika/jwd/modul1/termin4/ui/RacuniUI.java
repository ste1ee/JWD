package com.ftninformatika.jwd.modul1.termin4.ui;

import java.time.LocalDateTime;
import java.util.Map;

import com.ftninformatika.jwd.modul1.termin4.model.Prodavnica;
import com.ftninformatika.jwd.modul1.termin4.model.Racun;
import com.ftninformatika.jwd.modul1.termin4.model.Stavka;
import com.ftninformatika.jwd.modul1.termin4.util.Konzola;
import com.ftninformatika.jwd.modul1.termin4.util.Meni;
import com.ftninformatika.jwd.modul1.termin4.util.Meni.FunkcionalnaStavkaMenija;
import com.ftninformatika.jwd.modul1.termin4.util.Meni.IzlaznaStavkaMenija;
import com.ftninformatika.jwd.modul1.termin4.util.Meni.StavkaMenija;

public class RacuniUI {

	public static Racun pronalazenje() {
		prikazSvih();

		long id = Konzola.ocitajLong("Unesite ID racuna");

		Map<Long, Racun> racuni = Prodavnica.getRacuni();
		Racun racun = racuni.get(id);
		if (racun == null)
			Konzola.prikazi("Racun nije pronaÄ‘en!");

		return racun;
	}

	private static void prikazSvih() {
		Map<Long, Racun> racuni = Prodavnica.getRacuni();
		for (Racun r : racuni.values()) {
			System.out.println(r);
		}
	}

	private static void prikaz() {

		Racun racun = pronalazenje();
		if (racun == null)
			return;

		System.out.println(racun);
		// prikaz svih stavki datog racuna
		System.out.println("Stavke racuna:");
		StavkeUI.prikazSvihStavkiRacuna(racun.getStavke());
		/*
		 * isto kao prethodna linija for(Stavka stavka : racun.getStavke()) {
		 * System.out.println(stavka); }
		 */
	}

	private static void dodavanje() {
		// kreiranje
		long id = Prodavnica.nextRacunId();

		Racun racun = new Racun(id, LocalDateTime.now(), 0.0);

		// many-to-many veza, ali jednosmerna
		while (Konzola.ocitajIzbor("Da li zelite da dodate stavku raÄ�una")) {
			Stavka stavka = StavkeUI.dodavanje(id);
			if (stavka == null)
				continue;
			racun.addStavku(stavka);
			Konzola.prikazi("Stavka je dodata!");
		}
		// dodavanje u mapi
		Map<Long, Racun> racuni = Prodavnica.getRacuni();
		racuni.put(racun.getId(), racun);
		// Ä�uvanje u fajlu
		try {
			Prodavnica.sacuvaj();
			Konzola.prikazi("UspeÅ¡no dodavanje!");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static void izmena() {
		// pronalaÅ¾enje postojeÄ‡eg
		Racun racun = pronalazenje();
		if (racun == null)
			return;
		// prikaÅ¾i stavke datog raÄ�una
		StavkeUI.prikazSvihStavkiRacuna(racun.getStavke());

		// many-to-many veza
		while (racun.getStavke().size() > 0 && Konzola.ocitajIzbor("Da li Å¾elite da uklonite stavku raÄ�una")) {
			Stavka stavka = StavkeUI.pronalazenjeIzRacuna();
			if (stavka == null) {
				continue;
			}
			StavkeUI.brisanje(stavka.getId());
			racun.removeStavku(stavka);
			Konzola.prikazi("Stavka je uklonjena!");
		}
		// many-to-many veza
		while (Konzola.ocitajIzbor("Da li Å¾elite da izmenite postojeÄ‡u/dodate novu stavku raÄ�una")) {
			// ako id ne postoji onda se dodaje stavka
			Stavka stavka = StavkeUI.pronalazenjeIzRacuna();
			if (stavka == null) {
				System.out.println("Dodajete novu stavku.");
				StavkeUI.dodavanje(racun.getId());
				Konzola.prikazi("Stavka je dodata!");
			}
			// ako id postoji onda se menja stavka
			else {
				int kolicina = Konzola.ocitajInt("Unesite koliÄ�inu proizvoda");
				stavka.addKolicinu(kolicina);
				racun.addStavku(stavka);
				Konzola.prikazi("Stavka saÄ�uvana!");
			}

		}
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
		Racun racun = pronalazenje();
		if (racun == null)
			return;

		// razvezivanje
		racun.removeAllStavke();

		// brisanje
		Map<Long, Racun> racuni = Prodavnica.getRacuni();
		racuni.remove(racun.getId());
		// Ä�uvanje
		try {
			Prodavnica.sacuvaj();
			Konzola.prikazi("UspeÅ¡no brisanje!");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void meniAdmin() {
		Meni.pokreni("Racuni",
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
		Meni.pokreni("Racuni",
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

				} });
	}

}
