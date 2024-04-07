package com.ftninformatika.jwd.modul1.ui;

import java.io.IOException;
import java.util.Map;

import com.ftninformatika.jwd.modul1.uti.model.Prodavnica;
import com.ftninformatika.jwd.modul1.uti.model.Proizvod;
import com.ftninformatika.jwd.modul1.uti.model.Stavka;
import com.ftninformatika.jwd.modul1.util.Konzola;
import com.ftninformatika.jwd.modul1.util.Meni;
import com.ftninformatika.jwd.modul1.util.Meni.FunkcionalnaStavkaMenija;
import com.ftninformatika.jwd.modul1.util.Meni.IzlaznaStavkaMenija;
import com.ftninformatika.jwd.modul1.util.Meni.StavkaMenija;

public class StavkaUI {

	public static Stavka pronalazenje() {
		prikazSvih();
		long id = Konzola.ocitajLong("Unesite ID stavke");
		Map<Long, Stavka> stavke = Prodavnica.getStavke();
		Stavka stavka = stavke.get(id);
		if (stavka == null) {
			Konzola.prikazi("Nepostojeca stavka");
		}
		return stavka;
	}

	static void prikazSvih() {
		Map<Long, Stavka> stavke = Prodavnica.getStavke();
		for (Stavka s : stavke.values()) {
			System.out.println(s);
		}
	}

	private static void prikaz() {
		Stavka stavka = pronalazenje();
		if (stavka == null) {
			return;
		}
		System.out.println(stavka);
	}

	private static void dodavanje() {
		long id = Prodavnica.nextStavkaId();
		long idProizvoda = 0;
		int kolicina = 0;
		while (idProizvoda == 0) {
			idProizvoda = Konzola.ocitajLong("Unesite ID povezanog proizvoda");
		}
		while (kolicina == 0) {
			kolicina = Konzola.ocitajInt("Unesite kolicinu proizvoda");
		}

		Map<Long, Proizvod> proizvodi = Prodavnica.getProizvodi();
		Map<Long, Stavka> stavke = Prodavnica.getStavke();
		Stavka stavka = new Stavka(id, kolicina, proizvodi.get(idProizvoda));
		// stavka.setProizvod(proizvodi.get(idProizvoda));
		stavke.put(stavka.getId(), stavka);

		try {
			Prodavnica.sacuvaj();
			Konzola.prikazi("Uspesno dodato");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void izmena() {
		Stavka stavka = pronalazenje();

		if (stavka == null) {
			return;
		}
		int kolicina = 0;
		long idProizvoda = 0;
		while (kolicina == 0) {
			kolicina = Konzola.ocitajInt("Unesi kolicinu");
		}
		ProizvodUI.prikazSvih();
		while (idProizvoda == 0) {
			idProizvoda = Konzola.ocitajLong("Unesi ID povezanog proizvoda");
		}
		Map<Long, Proizvod> proizvodi = Prodavnica.getProizvodi();
		stavka.setKolicina(kolicina);
		stavka.setProizvod(proizvodi.get(idProizvoda));

		try {
			Prodavnica.sacuvaj();
			Konzola.prikazi("Uspesno izmenjeno");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void brisanje() {
		Stavka stavka = pronalazenje();
		if (stavka == null) {
			return;
		}
		Map<Long, Stavka> stavke = Prodavnica.getStavke();
		stavke.remove(stavka.getId());

		try {
			Prodavnica.sacuvaj();
			Konzola.prikazi("Uspesno izbrisano!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void meni() {
		Meni.pokreni("Stavke",
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
						ProizvodUI.prikazSvih();
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
