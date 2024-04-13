package com.ftninformatika.jwd.modul1.termin7.bioskop.ui;

import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.ftninformatika.jwd.modul1.termin7.bioskop.dao.KorisnikDAO;
import com.ftninformatika.jwd.modul1.termin7.bioskop.model.Korisnik;
import com.ftninformatika.jwd.modul1.util.Konzola;
import com.ftninformatika.jwd.modul1.util.Meni;
import com.ftninformatika.jwd.modul1.util.Meni.FunkcionalnaStavkaMenija;
import com.ftninformatika.jwd.modul1.util.Meni.IzlaznaStavkaMenija;
import com.ftninformatika.jwd.modul1.util.Meni.StavkaMenija;
import com.ftninformatika.jwd.modul1.util.Tabela;

public class KorisniciUI {

	static KorisnikDAO korisnikDAO;

	public static void setKorisnikDAO(KorisnikDAO korisnikDAO) {
		KorisniciUI.korisnikDAO = korisnikDAO;
	}

	private static final Tabela<Korisnik> TABELA = new Tabela<>("%20s %50s %10s %10s",
			new Object[] { "Korisnicko Ime", "Email", "Pol", "Administrator" }) {

		@Override
		protected List<Object[]> uredi(Korisnik vrednost) {
			List<Object[]> rezultat = new ArrayList<>();
			rezultat.add(new Object[] { vrednost.getKorisnickoIme(), vrednost.geteMail(), vrednost.getPol(),
					vrednost.getAdministrator() });

			return rezultat;
		}
	};

	public static Korisnik pronalazenje() throws Exception {
		prikazSvih();

		String korisnickoIme = Konzola.ocitajString("Unesite korisnicko ime korisnika");

		Korisnik korisnik = korisnikDAO.get(korisnickoIme);
		if (korisnik == null)
			Konzola.prikazi("Korisnik nije pronađen!");

		return korisnik;
	}

	private static void prikazSvih() {
		try {
			Collection<Korisnik> korisnici = korisnikDAO.getAll();
			TABELA.prikazi(korisnici);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Došlo je do greške!");
		}
	}

	private static void prikaz() {
		try {
			Korisnik korisnik = pronalazenje();
			if (korisnik == null)
				return;

			TABELA.prikazi(korisnik);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Došlo je do greške!");
		}
	}

	private static void dodavanje() {
		String korisnickoIme = "";
		while (korisnickoIme.equals("")) {
			String korisnickoImeTest = Konzola.ocitajString("Unesite unikatno korisnicko ime");
			try {
				if (korisnikDAO.get(korisnickoImeTest) != null) {
					Konzola.prikazi("Korisnicko ime je zauzeto!");
				} else if (korisnikDAO.get(korisnickoImeTest) == null)
					korisnickoIme = korisnickoImeTest;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String lozinka = "";
		while (lozinka.equals("")) {
			lozinka = Konzola.ocitajString("Unesite zeljenu lozinku");
		}

		String eMail = "";
		while (eMail.equals(""))
			eMail = Konzola.ocitajString("Unesite zeljenu eMail adresu");

		String pol = "";
		while (pol.equals(""))
			pol = Konzola.ocitajString("Unesite pol");

		boolean administrator = Konzola.ocitajIzbor("Da li je korisnik administrator", "d", "n");

		Korisnik korisnik = new Korisnik(korisnickoIme, lozinka, eMail, pol, administrator);
		try {
			// čuvanje
			korisnikDAO.add(korisnik);
			Konzola.prikazi("Uspešno dodavanje!");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Došlo je do greške!");
		}
	}

	private static void izmena() {
		try {
			// pronalaženje postojećeg
			Korisnik korisnik = pronalazenje();
			if (korisnik == null)
				return;

			// izmena
			String lozinka = korisnik.getLozinka();
			if (Konzola.ocitajIzbor("Da li zelita da promenite lozinku")) {
				lozinka = "";
				while (lozinka.equals(""))
					lozinka = Konzola.ocitajString("Unesite novu lozinku");
			}
			korisnik.setLozinka(lozinka);

			String eMail = korisnik.geteMail();
			if (Konzola.ocitajIzbor("Da li zelite da promenite eMail adresu")) {
				eMail = "";
				while (eMail.equals(""))
					eMail = Konzola.ocitajString("Unesite novu email adresu");
			}
			korisnik.seteMail(eMail);

			String pol = korisnik.getPol();
			if (Konzola.ocitajIzbor("Da li zelite da promenite pol korisnika")) {
				pol = "";
				while (pol.equals(""))
					pol = Konzola.ocitajString("Unesite zeljeni pol");
			}
			korisnik.setPol(pol);

			boolean administrator = korisnik.getAdministrator();
			if (Konzola.ocitajIzbor("Da li zelite da promenite vrstu korisnika")) {
				boolean izbor = Konzola.ocitajIzbor("Da li je korisnik administrator", "d", "n");
				if (izbor == true) {
					administrator = true;
				} else if (izbor == false) {
					administrator = false;
				}
			}
			korisnik.setAdministrator(administrator);

			// čuvanje
			korisnikDAO.update(korisnik);
			Konzola.prikazi("Uspešna izmena!");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Došlo je do greške!");
		}
	}

	private static void brisanje() {
		try {
			// pronalaženje postojećeg
			Korisnik korisnik = pronalazenje();
			if (korisnik == null)
				return;

			// čuvanje
			korisnikDAO.delete(korisnik.getKorisnickoIme());
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Došlo je do greške!");
		}
	}

	public static void meni() {
		Meni.pokreni("Korisnici",
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

}
