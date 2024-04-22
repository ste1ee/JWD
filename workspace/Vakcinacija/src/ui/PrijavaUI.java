package ui;

import java.time.LocalDate;
import java.util.Collection;

import com.mysql.cj.jdbc.exceptions.SQLError;

import dao.PrijavaDAO;
import model.Prijava;
import model.Vakcina;
import util.Konzola;

public class PrijavaUI {

	private static PrijavaDAO prijavaDAO;

	public static void setPrijavaDAO(PrijavaDAO prijavaDAO) {
		PrijavaUI.prijavaDAO = prijavaDAO;
	}

	public static void prikazSvih() {
		try {
			Collection<Prijava> prijave = prijavaDAO.getAll();
			for (Prijava itPrijava : prijave) {
				System.out.println(itPrijava);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void dodavanje() {
		
		String jmbg = "";
		while(jmbg.equals("")) {
			jmbg = Konzola.ocitajString("Unesite jmbg");
		}
		String imeIPrezime = Konzola.ocitajString("Unesite ime i prezime");
		Vakcina vakcina;
		try {
			vakcina = VakcinaUI.pronalazenje();
			if(vakcina == null)
				return;
			LocalDate datum = LocalDate.now();
			Prijava prijava = new Prijava(0, jmbg, imeIPrezime, vakcina, datum);
			prijavaDAO.add(prijava);
			vakcina.addPrijava(prijava);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
