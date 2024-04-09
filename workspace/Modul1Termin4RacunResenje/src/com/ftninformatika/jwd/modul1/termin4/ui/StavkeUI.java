package com.ftninformatika.jwd.modul1.termin4.ui;

import java.util.Collection;
import java.util.Map;

import com.ftninformatika.jwd.modul1.termin4.model.Prodavnica;
import com.ftninformatika.jwd.modul1.termin4.model.Proizvod;
import com.ftninformatika.jwd.modul1.termin4.model.Racun;
import com.ftninformatika.jwd.modul1.termin4.model.Stavka;
import com.ftninformatika.jwd.modul1.termin4.util.Konzola;
import com.ftninformatika.jwd.modul1.termin4.util.Meni;
import com.ftninformatika.jwd.modul1.termin4.util.Meni.FunkcionalnaStavkaMenija;
import com.ftninformatika.jwd.modul1.termin4.util.Meni.IzlaznaStavkaMenija;
import com.ftninformatika.jwd.modul1.termin4.util.Meni.StavkaMenija;

public class StavkeUI {

	public static Stavka pronalazenje() {
		
		prikazSvih();

		long id = Konzola.ocitajLong("Unesite ID stavke");
		// Prodavnica.getStavke() getter iz Prodavnice
		// Da nema gettera pristupali bi : Prodavnica.stavke
		Map<Long, Stavka> stavke = Prodavnica.getStavke();
		Stavka stavka = stavke.get(id);
		if (stavka == null)
			Konzola.prikazi("Stavka računa nije pronađena!");

		return stavka;
	}
	
	public static Stavka pronalazenjeIzRacuna() {

		long id = Konzola.ocitajLong("Unesite ID stavke");

		Map<Long, Racun> racuni = Prodavnica.getRacuni();
		Stavka stavka = null;
		boolean flag = false;
		//Map<Long, Stavka> stavke = Prodavnica.getStavke();
		for(Racun racun : racuni.values()) {
			for(Stavka s : racun.getStavke()) {
				if(s.getId() == id) {
					stavka = s;
					flag = true;
					break;
				}
			}
			if(flag) 
				break;
		}
		
		
		if (stavka == null)
			Konzola.prikazi("Stavka računa nije pronađena!");

		return stavka;
	}
	private static void prikazSvih() {
		Map<Long, Stavka> stavke = Prodavnica.getStavke();
		for(Stavka s: stavke.values()) {
			System.out.println(s);
		}

	}
	// ovde smo poslali set
	static void prikazSvihStavkiRacuna(Collection<Stavka> collection) {
		for(Stavka s: collection) {
			System.out.println(s);
		}
	}

	private static void prikaz() {
				
		Stavka stavka = pronalazenje();
		if (stavka == null)
			return;
		
		System.out.println(stavka);
	}
    // id racuna da bi nam bilo lakse za spajanje
	static Stavka dodavanje(long racunId) {
		// kreiranje
		
		long id = Prodavnica.nextStavkaId();
		//prikazi proizvode
		ProizvodiUI.prikazSvih();
		
		long proizvodId = Konzola.ocitajLong("Unesite ID proizvoda");
		
		Map<Long, Proizvod> proizvodi = Prodavnica.getProizvod();
		Proizvod proizvod = proizvodi.get(proizvodId);
             
		int kolicina = 0;
		kolicina = Konzola.ocitajInt("Unesite količinu proizvoda");

		Stavka stavka = new Stavka(id, proizvod, kolicina);
		// dodavanje
		Map<Long, Stavka> stavke = Prodavnica.getStavke();
		stavke.put(stavka.getId(), stavka);
		
		// čuvanje
		try {
			Prodavnica.sacuvaj();
			//Konzola.prikazi("Uspešno dodavanje!");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return stavka;
	}

	private static void izmena() {
		// pronalaženje postojećeg
		Stavka stavka = pronalazenje();
		if (stavka == null)
			return;

		int kolicina = 0;
		kolicina = Konzola.ocitajInt("Unesite količinu");
		stavka.setKolicina(kolicina);
		
		// čuvanje
		try {
			Prodavnica.sacuvaj();
			Konzola.prikazi("Uspešna izmena!");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	static void brisanje(long id) {
		// brisanje
		Map<Long, Stavka> stavke = Prodavnica.getStavke();
		stavke.remove(id);
		// čuvanje
		try {
			Prodavnica.sacuvaj();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void meni() {
		Meni.pokreni("Stavke", new StavkaMenija[] {
			new IzlaznaStavkaMenija("Povratak"),
			new FunkcionalnaStavkaMenija("Prikaz svih") {

				@Override
				public void izvrsi() { prikazSvih(); }

			},
			new FunkcionalnaStavkaMenija("Prikaz") {

				@Override
				public void izvrsi() { prikaz(); }

			},
//			new FunkcionalnaStavkaMenija("Dodavanje") {
//
//				@Override
//				public void izvrsi() {  }
//
//			},
//			new FunkcionalnaStavkaMenija("Izmena") {
//
//				@Override
//				public void izvrsi() { izmena(); }
//
//			},
//			new FunkcionalnaStavkaMenija("Brisanje") {
//
//				@Override
//				public void izvrsi() { brisanje(); }
//
//			}
		});
	}
}
