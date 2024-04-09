package ui;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import model.Igrac;
import model.Klub;
import model.Savez;
import util.Konzola;
import util.Meni;
import util.Meni.FunkcionalnaStavkaMenija;
import util.Meni.IzlaznaStavkaMenija;
import util.Meni.StavkaMenija;

public class KlubUI {

	public static Klub pronalazenje() {
		prikazSvih();
		int sifra = Konzola.ocitajInt("Unesite sifru kluba");
		Map<Integer, Klub> klubovi = Savez.getKlubovi();
		Klub klub = klubovi.get(sifra);
		if (klub == null)
			Konzola.prikazi("Klub nije pronadjen");
		return klub;
	}

	static void prikazSvih() {
		Map<Integer, Klub> klubovi = Savez.getKlubovi();
		for (Klub k : klubovi.values())
			System.out.println(k);
	}

	private static void dodavanje() {
		int sifra = Savez.nextKlubSifra();
		String naziv = "";
		while (naziv.equals(""))
			naziv = Konzola.ocitajString("Unesite naziv kluba");
		Klub klub = new Klub(sifra, naziv);
		Map<Integer, Igrac> igraci = Savez.getIgraci();
		if (Konzola.ocitajIzbor("Da li zelite da dodate igraca")) {
			for (Igrac i : igraci.values()) {
				System.out.println(i);
			}
			int sifraIgraca = 0;
			while (sifraIgraca == 0) {
				sifraIgraca = Konzola.ocitajInt("Unesite sifru igraca");
				if (Savez.getIgraci().get(sifraIgraca) == null)
					Konzola.prikazi("Nije pronadjen igrac");
			}
			Igrac igrac = Savez.getIgraci().get(sifraIgraca);
			klub.addIgrac(igrac);
		}

		Map<Integer, Klub> klubovi = Savez.getKlubovi();
		klubovi.put(klub.getSifra(), klub);
	}

	private static void izmena() {
		Klub klub = pronalazenje();
		if (klub == null)
			return;
		boolean izbor = Konzola.ocitajIzbor("Sta zelite da izmenite", "naziv", "igrace");
		if (izbor == true)
			klub.setNaziv(Konzola.ocitajString("Unesite naziv"));

		if (izbor == false) {
			Collection<Igrac> sviigraci = klub.getSviigraci();
			Map<Integer, Igrac> igraci = Savez.getIgraci();
			boolean izbor2 = Konzola.ocitajIzbor("Sta zelite da uradite sa igracima", "dodaj", "izbrisi");
			if (izbor2 == true) {
				for (Igrac i : igraci.values()) {
					System.out.println(i);
				}
				int sifraIgraca = 0;
				while (sifraIgraca == 0) {
					sifraIgraca = Konzola.ocitajInt("Unesite sifru igraca");
					if (Savez.getIgraci().get(sifraIgraca) == null)
						Konzola.prikazi("Nije pronadjen igrac");
				}
				Igrac igrac = Savez.getIgraci().get(sifraIgraca);
				klub.addIgrac(igrac);
			}
			if (izbor2 == false) {
				for (Igrac s : sviigraci) {
					System.out.println(s);
				}
				int sifraIgraca = 0;
				while (sifraIgraca == 0) {
					sifraIgraca = Konzola.ocitajInt("Unesite sifru igraca");
				}
				Igrac igrac = Savez.getIgraci().get(sifraIgraca);
				klub.removeIgrac(igrac);
			}
		}

		try {
			Savez.sacuvaj();
			Konzola.prikazi("Uspesno izmenjen klub");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void brisanje() {
		Klub klub = pronalazenje();
		if (klub == null)
			return;
		Map<Integer, Klub> klubovi = Savez.getKlubovi();
		klubovi.remove(klub.getSifra());

		try {
			Savez.sacuvaj();
			Konzola.prikazi("Klub uspesno izbrisan");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void meni() {
		Meni.pokreni("Klubovi",
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
