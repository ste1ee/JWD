package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import model.Klub;
import model.Savez;
import model.Sudija;
import model.Utakmica;
import util.Konzola;
import util.Meni;
import util.Meni.FunkcionalnaStavkaMenija;
import util.Meni.IzlaznaStavkaMenija;
import util.Meni.StavkaMenija;

public class UtakmicaUI {

	public static Utakmica pronalazenje() {
		prikazSvih();
		int sifra = Konzola.ocitajInt("Unesite sifru utakmice");
		Map<Integer, Utakmica> utakmice = Savez.getUtakmice();
		Utakmica utakmica = utakmice.get(sifra);
		if (utakmica == null)
			Konzola.prikazi("Utakmica nije pronadjena!");
		return utakmica;
	}

	static void prikazSvih() {
		Map<Integer, Utakmica> utakmice = Savez.getUtakmice();
		for (Utakmica u : utakmice.values())
			System.out.println(u);
	}

	private static void dodavanje() {
		int sifra = Savez.nextUtakmicaSifra();
		String datum = "";
		while (datum.equals(""))
			datum = Konzola.ocitajString("Unesite datum");
		String vreme = "";
		while (vreme.equals(""))
			vreme = Konzola.ocitajString("Unesite vreme");
		ArrayList<Sudija> sudije = new ArrayList<>();
		Utakmica utakmica = new Utakmica(sifra, datum, vreme, null, null, sudije);
		if (Konzola.ocitajIzbor("Da li zelite da dodate klubove")) {
			Map<Integer, Klub> klubovi = Savez.getKlubovi();
			Klub domacin = null;
			Klub gost = null;
			int domacinSifra = 0;
			int gostSifra = 0;
			for (Klub k : klubovi.values())
				System.out.println(k);
			domacinSifra = Konzola.ocitajInt("Unesite sifru domacina");
			gostSifra = Konzola.ocitajInt("Unesite sifru gosta");

			domacin = klubovi.get(domacinSifra);
			gost = klubovi.get(gostSifra);

			utakmica.setDomacin(domacin);
			utakmica.setGost(gost);
		}

		if (Konzola.ocitajIzbor("Da li zelite da dodate sudiju")) {
			Map<Integer, Sudija> sudije2 = Savez.getSudije();
			for (Sudija s : sudije2.values())
				System.out.println(s);
			int sudijaSifra = Konzola.ocitajInt("Unesite sifru sudije");
			Sudija sudija = sudije2.get(sudijaSifra);
			utakmica.addSudija(sudija);

		}

		try {
			Savez.sacuvaj();
			Konzola.prikazi("Uspesno dodata utakmica");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void izmena() {
		Utakmica utakmica = pronalazenje();
		if (utakmica == null)
			return;
		boolean izbor = Konzola.ocitajIzbor("Sta zelite da izmenite", "datum i vreme", "klubovi i sudije");
		if (izbor == true) {
			String datum = "";
			datum = Konzola.ocitajString("Unesite datum");
			String vreme = "";
			vreme = Konzola.ocitajString("Unesite vreme");
			utakmica.setDatum(datum);
			utakmica.setVreme(vreme);
		}
		if (izbor == false) {
			boolean izbor2 = Konzola.ocitajIzbor("Sta zelite da izmenite", "klubovi", "sudije");
			if (izbor2 == true) {
				Map<Integer, Klub> klubovi = Savez.getKlubovi();
				boolean izbor3 = Konzola.ocitajIzbor("Koji klub hocete da promenite", "domacin", "gost");
				if (izbor3 == true) {
					for (Klub k : klubovi.values())
						System.out.println(k);
					int klubSifra = 0;
					klubSifra = Konzola.ocitajInt("Unesite sifru novog kluba domacin");
					Klub klubDomacin = klubovi.get(klubSifra);
					utakmica.setDomacin(klubDomacin);
					if (Konzola.ocitajIzbor("Da li zelite da promenite i gosta?")) {
						for (Klub k : klubovi.values())
							System.out.println(k);
						int klubSifra2 = 0;
						klubSifra2 = Konzola.ocitajInt("Unesite sifru novog kluba gost");
						Klub klubGost = klubovi.get(klubSifra2);
						utakmica.setGost(klubGost);

					}
				}
				if (izbor3 == false) {
					for (Klub k : klubovi.values())
						System.out.println(k);
					int klubSifra = 0;
					klubSifra = Konzola.ocitajInt("Unesite sifru novog kluba gost");
					Klub klubGost = klubovi.get(klubSifra);
					utakmica.setGost(klubGost);
					if (Konzola.ocitajIzbor("Da li zelite da promenite i domacina")) {
						for (Klub k : klubovi.values())
							System.out.println(k);
						int klubSifra2 = 0;
						klubSifra2 = Konzola.ocitajInt("Unesite sifru novog kluba domacin");
						Klub klubDomacin = klubovi.get(klubSifra2);
						utakmica.setDomacin(klubDomacin);
					}
				}
			}
			if (izbor2 == false) {
				Map<Integer, Sudija> sudije = Savez.getSudije();
				boolean izbor3 = Konzola.ocitajIzbor("Sta zelite da uradite", "dodaj", "skloni");
				if (izbor3 == true) {
					for (Sudija s : sudije.values())
						System.out.println(s);
					int sifra = 0;
					sifra = Konzola.ocitajInt("Unesite sifru novog sudije");
					Sudija sudija = sudije.get(sifra);
					utakmica.addSudija(sudija);
				}
				if (izbor3 == false) {
					Collection<Sudija> sudije2 = utakmica.getSveSudije();
					for (Sudija s : sudije2)
						System.out.println(s);
					int sifra = 0;
					sifra = Konzola.ocitajInt("Unesite sifru sudije");
					Sudija sudija = sudije.get(sifra);
					utakmica.removeSudija(sudija);
				}

			}
		}

		try {
			Savez.sacuvaj();
			Konzola.prikazi("Uspesna izmena");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void brisanje() {
		Map<Integer, Utakmica> utakmice = Savez.getUtakmice();
		for (Utakmica u : utakmice.values())
			System.out.println(u);
		@SuppressWarnings("unused")
		int sifra = 0;
		sifra = Konzola.ocitajInt("Unesite sifru utakmice");
		Utakmica utakmica = pronalazenje();
		if (utakmica == null)
			return;
		utakmice.remove(utakmica.getSifra());

		try {
			Savez.sacuvaj();
			Konzola.prikazi("Uspesno izbrisano");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void meni() {
		Meni.pokreni("Utakmice",
				new StavkaMenija[] { new IzlaznaStavkaMenija("Povratak"), new FunkcionalnaStavkaMenija("Prikazi sve") {
					@Override
					public void izvrsi() {
						prikazSvih();
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
