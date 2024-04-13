package com.ftninformatika.jwd.modul1.termin7.bioskop.ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.ftninformatika.jwd.modul1.termin7.bioskop.dao.ProjekcijaDAO;
import com.ftninformatika.jwd.modul1.termin7.bioskop.model.Projekcija;
import com.ftninformatika.jwd.modul1.util.Konzola;
import com.ftninformatika.jwd.modul1.util.Meni;
import com.ftninformatika.jwd.modul1.util.Tabela;
import com.ftninformatika.jwd.modul1.util.Meni.FunkcionalnaStavkaMenija;
import com.ftninformatika.jwd.modul1.util.Meni.IzlaznaStavkaMenija;
import com.ftninformatika.jwd.modul1.util.Meni.StavkaMenija;

public class ProjekcijeUI {

	private static ProjekcijaDAO projekcijaDAO;

	public static void setProjekcijaDAO(ProjekcijaDAO projekcijaDAO) {
		ProjekcijeUI.projekcijaDAO = projekcijaDAO;
	}

	private static final Tabela<Projekcija> TABELA = new Tabela<>("%5s %20s %-50s %5s %5s %10s",
			new Object[] { "ID", "Datum i vreme", "Film", "Sala", "Tip", "Cena karte" }) {

		@Override
		protected List<Object[]> uredi(Projekcija vrednost) {
			List<Object[]> rezultat = new ArrayList<>();
			rezultat.add(new Object[] { vrednost.getId(), Konzola.formatiraj(vrednost.getDatumIVreme()),
					(vrednost.getFilm() != null) ? vrednost.getFilm().getNaziv() : "", // many-to-one veza
					vrednost.getSala(), vrednost.getTip(), vrednost.getCenaKarte() });
			return rezultat;
		}

	};

	public static Projekcija pronalazenje() throws Exception {
		prikazSvih();

		long id = Konzola.ocitajLong("Unesite ID projekcije");

		Projekcija projekcija = projekcijaDAO.get(id);
		if (projekcija == null)
			Konzola.prikazi("Projekcija nije pronaÄ‘ena!");

		return projekcija;
	}

	private static void prikazSvih() {
		try {
			Collection<Projekcija> projekcije = projekcijaDAO.getAll();
			TABELA.prikazi(projekcije);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("DoÅ¡lo je do greÅ¡ke!");
		}
	}

	private static void prikaz() {
		try {
			Projekcija projekcija = pronalazenje();
			if (projekcija == null)
				return;

			TABELA.prikazi(projekcija);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("DoÅ¡lo je do greÅ¡ke!");
		}
	}

	private static void dodavanje() {
		// kreiranje
		LocalDateTime datumIVreme = LocalDateTime.now();
		while (datumIVreme.compareTo(LocalDateTime.now()) <= 0)
			datumIVreme = Konzola.ocitajDateTime("Unesite datum i vreme");

		int sala = 0;
		while (sala <= 0 || sala > 3)
			sala = Konzola.ocitajInt("Unesite salu");

		String tip = "";
		while (!(tip.equals("2D") || tip.equals("3D") || tip.equals("4D")))
			tip = Konzola.ocitajString("Unesite tip");

		double cenaKarte = 0;
		while (cenaKarte <= 0)
			cenaKarte = Konzola.ocitajDouble("Unesite cenu karte");

		Projekcija projekcija = new Projekcija(datumIVreme, null, sala, tip, cenaKarte);
		try {
			// Ä�uvanje
			projekcijaDAO.add(projekcija);
			Konzola.prikazi("UspeÅ¡no dodavanje!");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("DoÅ¡lo je do greÅ¡ke!");
		}
	}

	private static void izmena() {
		try {
			// pronalaÅ¾enje postojeÄ‡eg
			Projekcija projekcija = pronalazenje();
			if (projekcija == null)
				return;

			// izmena
			LocalDateTime datumIVreme = LocalDateTime.now();
			while (datumIVreme.compareTo(LocalDateTime.now()) <= 0)
				datumIVreme = Konzola.ocitajDateTime("Unesite datum i vreme");
			projekcija.setDatumIVreme(datumIVreme);

			int sala = 0;
			while (sala <= 0 || sala > 3)
				sala = Konzola.ocitajInt("Unesite salu");
			projekcija.setSala(sala);

			String tip = "";
			while (!(tip.equals("2D") || tip.equals("3D") || tip.equals("4D")))
				tip = Konzola.ocitajString("Unesite tip");
			projekcija.setTip(tip);

			double cenaKarte = 0;
			while (cenaKarte <= 0)
				cenaKarte = Konzola.ocitajDouble("Unesite cenu karte");
			projekcija.setCenaKarte(cenaKarte);

			// Ä�uvanje
			projekcijaDAO.update(projekcija);
			Konzola.prikazi("UspeÅ¡na izmena!");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("DoÅ¡lo je do greÅ¡ke!");
		}
	}

	private static void brisanje() {
		try {
			// pronalaÅ¾enje postojeÄ‡eg
			Projekcija projekcija = pronalazenje();
			if (projekcija == null)
				return;

			// Ä�uvanje
			projekcijaDAO.delete(projekcija.getId());
			Konzola.prikazi("UspeÅ¡no brisanje!");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("DoÅ¡lo je do greÅ¡ke!");
		}
	}

	public static void meni() {
		Meni.pokreni("Projekcije",
				new StavkaMenija[] { new IzlaznaStavkaMenija("Porvratak"), new FunkcionalnaStavkaMenija("Prikaz svih") {

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
