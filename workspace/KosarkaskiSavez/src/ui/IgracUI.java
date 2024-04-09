package ui;

import java.io.IOException;
import java.util.Map;

import model.Igrac;
import model.Savez;
import util.Konzola;
import util.Meni;
import util.Meni.FunkcionalnaStavkaMenija;
import util.Meni.IzlaznaStavkaMenija;
import util.Meni.StavkaMenija;

public class IgracUI {

	public static Igrac pronalazenje() {
		prikazSvih();
		int sifra = Konzola.ocitajInt("Unesite sifru igraca");
		Map<Integer, Igrac> igraci = Savez.getIgraci();
		Igrac igrac = igraci.get(sifra);
		if (igrac == null)
			Konzola.prikazi("Igrac nije pronadjen!");
		return igrac;
	}

	static void prikazSvih() {
		Map<Integer, Igrac> igraci = Savez.getIgraci();
		for (Igrac i : igraci.values())
			System.out.println(i);
	}

	private static void dodavanje() {
		int sifra = Savez.nextIgracSifra();
		String ime = "";
		while (ime.equals(""))
			ime = Konzola.ocitajString("Unesite ime igraca");
		String prezime = "";
		while (prezime.equals(""))
			prezime = Konzola.ocitajString("Unesite prezime igraca");
		Igrac igrac = new Igrac(sifra, ime, prezime);
		Map<Integer, Igrac> igraci = Savez.getIgraci();
		igraci.put(igrac.getSifra(), igrac);

		try {
			Savez.sacuvaj();
			Konzola.prikazi("Uspesno dodat novi igrac!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void izmena() {
		Igrac igrac = pronalazenje();
		if (igrac == null)
			return;
		boolean izbor = Konzola.ocitajIzbor("Sta zelite da izmenite", "ime", "prezime");
		if (izbor == true)
			igrac.setIme(Konzola.ocitajString("Unesite ime igraca"));

		if (izbor == false)
			igrac.setPrezime(Konzola.ocitajString("Unesite prezime igraca"));

		try {
			Savez.sacuvaj();
			Konzola.prikazi("Uspesno izmenjen igrac");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void brisanje() {
		Igrac igrac = pronalazenje();
		if (igrac == null)
			return;
		Map<Integer, Igrac> igraci = Savez.getIgraci();
		igraci.remove(igrac.getSifra());

		try {
			Savez.sacuvaj();
			Konzola.prikazi("Igrac uspesno izbrisan");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void meni() {
		Meni.pokreni("Igraci",
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
