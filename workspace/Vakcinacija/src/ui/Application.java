package ui;

import java.sql.Connection;
import java.sql.DriverManager;

import dao.PrijavaDAO;
import dao.VakcinaDAO;
import dao.impl.DatabasePrijavaDAO;
import dao.impl.DatabaseVakcinaDAO;
import util.Meni;
import util.Meni.FunkcionalnaStavkaMenija;
import util.Meni.IzlaznaStavkaMenija;
import util.Meni.StavkaMenija;

public class Application {
	private static void initDatabase() throws Exception {
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/vakcinisanje?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Europe/Belgrade",
				"root", "root");

		VakcinaDAO vakcinaDAO = new DatabaseVakcinaDAO(conn);
		PrijavaDAO prijavaDAO = new DatabasePrijavaDAO(conn);
		VakcinaUI.setVakcinaDAO(vakcinaDAO);
		PrijavaUI.setPrijavaDAO(prijavaDAO);
		// IzvestavanjeUI.setVakcinaDAO(vakcinaDAO);
	}

	static {
		try {
			initDatabase();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Greška pri povezivanju sa izvorom podataka!");

			System.exit(1);
		}
	}

	public static void main(String[] args) throws Exception {
		Meni.pokreni("Vakcinisanje", new StavkaMenija[] { new IzlaznaStavkaMenija("Izlaz"),
				new FunkcionalnaStavkaMenija("Prikaz svih vakcina") {

					@Override
					public void izvrsi() {
						VakcinaUI.prikazSvih();
					}

				}, new FunkcionalnaStavkaMenija("Dodavanje prijave") {

					@Override
					public void izvrsi() {
						PrijavaUI.dodavanje();
					}

				}, new FunkcionalnaStavkaMenija("Prikaz svih prijava sa vakcinama") {

					@Override
					public void izvrsi() {
						PrijavaUI.prikazSvih();
					}

				}, new FunkcionalnaStavkaMenija("Izvestavanje") {

					@Override
					public void izvrsi() {
						// IzvestavanjeUI.izvestavanje();
					}

				} });
	}
}
