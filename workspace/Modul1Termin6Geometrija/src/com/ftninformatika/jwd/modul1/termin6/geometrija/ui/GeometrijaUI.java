package com.ftninformatika.jwd.modul1.termin6.geometrija.ui;

import java.util.ArrayList;
import java.util.List;

import com.ftninformatika.jwd.modul1.termin6.geometrija.model.GeometrijskiOblik;
import com.ftninformatika.jwd.modul1.termin6.geometrija.model.Poligon;
import com.ftninformatika.jwd.modul1.termin6.geometrija.model.impl.Krug;
import com.ftninformatika.jwd.modul1.termin6.geometrija.model.impl.Kvadrat;
import com.ftninformatika.jwd.modul1.termin6.geometrija.model.impl.Pravougaonik;
import com.ftninformatika.jwd.modul1.termin6.geometrija.model.impl.Trougao;
import com.ftninformatika.jwd.modul1.util.Tabela;

public class GeometrijaUI {

	private static final Tabela<GeometrijskiOblik> TABELA = new Tabela<>("%-15s %10s %10s %15s",
			new Object[] { "Naziv", "Obim", "Povrsina", "Br. stranica" }) {

		@Override
		protected List<Object[]> uredi(GeometrijskiOblik vrednost) {
			String obim = String.format("%.2f", vrednost.obim());
			String povrsina = String.format("%.2f", vrednost.povrsina());
			String brStranica = "";
			if (vrednost instanceof Poligon poligon) {
				brStranica = String.valueOf(poligon.brojStranica());
			}

			List<Object[]> rezultat = new ArrayList<>();
			rezultat.add(new Object[] { vrednost.naziv(), obim, povrsina, brStranica });
			return rezultat;
		}

	};

	public static void main(String[] args) {
		List<GeometrijskiOblik> oblici = new ArrayList<>();
		oblici.add(new Kvadrat(10.0));
		oblici.add(new Krug(10.0));
		oblici.add(new Trougao(10.0, 10.0, 10.0));
		oblici.add(new Pravougaonik(10.0, 20.0));

		double ukupanObim = 0.0;
		double ukupnaPovrsina = 0.0;
		for (GeometrijskiOblik itOblik : oblici) {
			ukupanObim += itOblik.obim();
			ukupnaPovrsina += itOblik.povrsina();
		}

		TABELA.prikazi(oblici);
		System.out.println(String.format("%-15s %10s %10s", "UKUPNO:", String.format("%.2f", ukupanObim),
				String.format("%.2f", ukupnaPovrsina)));
		/*
		 * for (GeometrijskiOblik itOblik: oblici) { System.out.println(itOblik); }
		 * System.out.printf("Ukupan obim: %.2f%n", ukupanObim);
		 * System.out.printf("Ukupna površina: %.2f%n", ukupnaPovrsina);
		 */
	}

}
