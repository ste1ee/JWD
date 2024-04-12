package com.ftninformatika.jwd.modul1.termin7.bioskop.ui;

import com.ftninformatika.jwd.modul1.termin7.bioskop.dao.FilmDAO;
import com.ftninformatika.jwd.modul1.termin7.bioskop.dao.ProjekcijaDAO;
import com.ftninformatika.jwd.modul1.termin7.bioskop.dao.ZanrDAO;
import com.ftninformatika.jwd.modul1.termin7.bioskop.dao.impl.file.FileFilmDAO;
import com.ftninformatika.jwd.modul1.termin7.bioskop.dao.impl.file.FileProjekcijaDAO;
import com.ftninformatika.jwd.modul1.termin7.bioskop.dao.impl.file.FileZanrDAO;
import com.ftninformatika.jwd.modul1.termin7.bioskop.model.Bioskop;
import com.ftninformatika.jwd.modul1.util.Meni;
import com.ftninformatika.jwd.modul1.util.Meni.FunkcionalnaStavkaMenija;
import com.ftninformatika.jwd.modul1.util.Meni.IzlaznaStavkaMenija;
import com.ftninformatika.jwd.modul1.util.Meni.StavkaMenija;

public class Application {

	private static void initFile() throws Exception {
		Bioskop.ucitaj();

		ZanrDAO zanrDAO = new FileZanrDAO();
		FilmDAO filmDAO = new FileFilmDAO();
		ProjekcijaDAO projekcijaDAO = new FileProjekcijaDAO();

		ZanroviUI.setZanrDAO(zanrDAO);
		FilmoviUI.setFilmDAO(filmDAO);
		ProjekcijeUI.setProjekcijaDAO(projekcijaDAO);
	}

	private static void initDatabase() throws Exception {

	}
	
	static {
		try {
			initFile();
			//initDatabase();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Greška pri povezivanju sa izvorom podataka!");
			
			System.exit(1); // prekid programa (u suprotnom bi se započela main metoda)
		}
	}

	public static void main(String[] args) {
		Meni.pokreni("Bioskop", new StavkaMenija[] {
			new IzlaznaStavkaMenija("Izlaz"),
			new FunkcionalnaStavkaMenija("Žanrovi") {

				@Override
				public void izvrsi() { ZanroviUI.meni(); }
				
			}, 
			new FunkcionalnaStavkaMenija("Filmovi") {

				@Override
				public void izvrsi() { FilmoviUI.meni(); }
				
			}, 
			new FunkcionalnaStavkaMenija("Projekcije") {

				@Override
				public void izvrsi() { ProjekcijeUI.meni(); }
				
			}
		});
	}

}
