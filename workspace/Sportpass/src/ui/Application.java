package ui;

import java.sql.Connection;
import java.sql.DriverManager;

import dao.ClanarinaDAO;
import dao.PaketDAO;
import dao.impl.DatabaseClanarinaDAO;
import dao.impl.DatabasePaketDAO;
import util.Meni;
import util.Meni.FunkcionalnaStavkaMenija;
import util.Meni.IzlaznaStavkaMenija;
import util.Meni.StavkaMenija;

public class Application {

	private static void initDatabase() throws Exception {
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/sportpass?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Europe/Belgrade",
				"root", "root");

		PaketDAO paketDAO = new DatabasePaketDAO(conn);
		ClanarinaDAO clanarinaDAO = new DatabaseClanarinaDAO(conn);
		PaketUI.setPaketDAO(paketDAO);
		ClanarinaUI.setClanarinaDAO(clanarinaDAO);
		// IzvestavanjeUI.setVozDAO(vozDAO);
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
		Meni.pokreni("SportPass", new StavkaMenija[] { new IzlaznaStavkaMenija("Izlaz"),
				new FunkcionalnaStavkaMenija("Prikaz odabranog paketa") {

					@Override
					public void izvrsi() {
						PaketUI.prikaz();
					}

				}, new FunkcionalnaStavkaMenija("Prikaz svih clanarina sa paketima") {

					@Override
					public void izvrsi() {
						ClanarinaUI.prikazSvih();
					}

				}, new FunkcionalnaStavkaMenija("Dodavanje clanarine") {

					@Override
					public void izvrsi() {
						ClanarinaUI.dodavanje();
					}

				},
				/*
				 * new FunkcionalnaStavkaMenija("Izvestavanje") {
				 * 
				 * @Override public void izvrsi() { //IzvestavanjeUI.izvestavanje(); }
				 * 
				 * }
				 */
		});
	}

}
