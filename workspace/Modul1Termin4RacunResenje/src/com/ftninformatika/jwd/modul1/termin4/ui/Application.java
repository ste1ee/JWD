package com.ftninformatika.jwd.modul1.termin4.ui;

import java.util.Map;

import com.ftninformatika.jwd.modul1.termin4.model.Admin;
import com.ftninformatika.jwd.modul1.termin4.model.Korisnik;
import com.ftninformatika.jwd.modul1.termin4.model.Prodavnica;
import com.ftninformatika.jwd.modul1.termin4.model.Radnik;
import com.ftninformatika.jwd.modul1.termin4.util.Konzola;
import com.ftninformatika.jwd.modul1.termin4.util.Meni;
import com.ftninformatika.jwd.modul1.termin4.util.Meni.FunkcionalnaStavkaMenija;
import com.ftninformatika.jwd.modul1.termin4.util.Meni.IzlaznaStavkaMenija;
import com.ftninformatika.jwd.modul1.termin4.util.Meni.StavkaMenija;

public class Application {

	private static Korisnik ulogujSe() {
		boolean logovanje = true;
		Korisnik korisnik = null;
		while (logovanje == true) {
			String korisnickoIme = Konzola.ocitajString("Unesite korisnicko ime");
			String lozinka = Konzola.ocitajString("Unesite vasu lozinku");
			Map<String, Korisnik> korisnici = Prodavnica.getKorisnici();
			korisnik = korisnici.get(korisnickoIme);
			if (lozinka.equals(korisnik.getLozinka())) {
				logovanje = false;
			} else {
				Konzola.prikazi("KorisnickoIme/Lozinka nisu bili tacni");
			}
		}
		return korisnik;

	}
	public static void meniAdmin() {
		Meni.pokreni("Prodavnica",
				new StavkaMenija[] { new IzlaznaStavkaMenija("Izlaz"), new FunkcionalnaStavkaMenija("Kategorije") {
					
					@Override
					public void izvrsi() {
						KategorijeUI.meniAdmin();
					}
					
				}, new FunkcionalnaStavkaMenija("Stavke") {
					
					@Override
					public void izvrsi() {
						StavkeUI.meni();
					}
					
				}, new FunkcionalnaStavkaMenija("Racuni") {
					
					@Override
					public void izvrsi() {
						RacuniUI.meniAdmin();
					}
					
				}, new FunkcionalnaStavkaMenija("Proizvodi") {
					
					@Override
					public void izvrsi() {
						ProizvodiUI.meniAdmin();
					}
					
				} });
		
	}
	
	public static void meniRadnik() {
		Meni.pokreni("Prodavnica",
				new StavkaMenija[] { new IzlaznaStavkaMenija("Izlaz"), new FunkcionalnaStavkaMenija("Kategorije") {
					
					@Override
					public void izvrsi() {
						KategorijeUI.meniRadnik();
					}
					
				}, new FunkcionalnaStavkaMenija("Stavke") {
					
					@Override
					public void izvrsi() {
						StavkeUI.meni();
					}
					
				}, new FunkcionalnaStavkaMenija("Racuni") {
					
					@Override
					public void izvrsi() {
						RacuniUI.meniRadnik();
					}
					
				}, new FunkcionalnaStavkaMenija("Proizvodi") {
					
					@Override
					public void izvrsi() {
						ProizvodiUI.meniRadnik();
					}
					
				} });
	}

	public static void main(String[] args) throws Exception {
		Prodavnica.ucitaj();
		
		Korisnik korisnik = ulogujSe();
		if(korisnik instanceof Admin) {
			Konzola.prikazi("USPESNO ULOGOVAN ADMIN");
			meniAdmin();
		}else if(korisnik instanceof Radnik) {
			Konzola.prikazi("USPESNO ULOGOVAN RADNIK");
			meniRadnik();
		}else {
			Konzola.prikazi("Izgleda da nemate pristup prodavnici");
		}

	}

}
