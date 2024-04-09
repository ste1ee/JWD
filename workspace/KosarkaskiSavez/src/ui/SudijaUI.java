package ui;

import java.io.IOException;
import java.util.Map;

import model.Savez;
import model.Sudija;
import util.Konzola;
import util.Meni;
import util.Meni.FunkcionalnaStavkaMenija;
import util.Meni.IzlaznaStavkaMenija;
import util.Meni.StavkaMenija;

public class SudijaUI {

	public static Sudija pronalazenje() {
		prikazSvih();
		int sifra = Konzola.ocitajInt("Unesite sifru sudije");
		Map<Integer, Sudija> sudije = Savez.getSudije();
		Sudija sudija = sudije.get(sifra);
		if (sudija == null)
			Konzola.prikazi("Sudija nije pronadjen!");
		return sudija;
	}

	static void prikazSvih() {
		Map<Integer, Sudija> sudije = Savez.getSudije();
		for (Sudija s : sudije.values())
			System.out.println(s);
	}

	private static void dodavanje() {
		int sifra = Savez.nextSudijaSifra();
		String ime = "";
		while (ime.equals(""))
			ime = Konzola.ocitajString("Unesite ime sudije");
		String prezime = "";
		while (prezime.equals(""))
			prezime = Konzola.ocitajString("Unesite prezime sudije");
		Sudija sudija = new Sudija(sifra, ime, prezime);
		Map<Integer, Sudija> sudije = Savez.getSudije();
		sudije.put(sudija.getSifra(), sudija);

		try {
			Savez.sacuvaj();
			Konzola.prikazi("Uspesno dodat sudija");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void izmena() {
		Sudija sudija = pronalazenje();
		if (sudija == null)
			return;
		boolean izbor = Konzola.ocitajIzbor("Sta zelite da izmenite", "ime", "prezime");
		if (izbor == true)
			sudija.setIme(Konzola.ocitajString("Unesite ime sudije"));
		if (izbor == false)
			sudija.setPrezime(Konzola.ocitajString("Unesite prezime sudije"));

		try {
			Savez.sacuvaj();
			Konzola.prikazi("Sudija uspesno izmenjen");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void brisanje() {
		Sudija sudija = pronalazenje();
		if (sudija == null)
			return;
		Map<Integer, Sudija> sudije = Savez.getSudije();
		sudije.remove(sudija.getSifra());

		try {
			Savez.sacuvaj();
			Konzola.prikazi("Uspesno izbrisan sudija");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void meni() {
		Meni.pokreni("Sudije",
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
