package ui;

import java.sql.Connection;
import java.sql.DriverManager;

import dao.KartaDAO;
import dao.VozDAO;
import dao.impl.database.DatabaseKartaDAO;
import dao.impl.database.DatabaseVozDAO;
import util.Meni;
import util.Meni.FunkcionalnaStavkaMenija;
import util.Meni.IzlaznaStavkaMenija;
import util.Meni.StavkaMenija;

public class Application {
	private static void initDatabase() throws Exception {
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/voz?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Europe/Belgrade",
				"root", "root");

		VozDAO vozDAO = new DatabaseVozDAO(conn);
		KartaDAO kartaDAO = new DatabaseKartaDAO(conn);
		VozUI.setVozDAO(vozDAO);
		KartaUI.setKartaDAO(kartaDAO);
		IzvestavanjeUI.setVozDAO(vozDAO);
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
		Meni.pokreni("Voz", new StavkaMenija[] { new IzlaznaStavkaMenija("Izlaz"),
				new FunkcionalnaStavkaMenija("Prikaz svih vozova") {

					@Override
					public void izvrsi() {
						VozUI.prikazSvih();
					}

				}, new FunkcionalnaStavkaMenija("Prikaz jednog voza sa prodatim kartama") {

					@Override
					public void izvrsi() {
						VozUI.prikaz();
					}

				}, new FunkcionalnaStavkaMenija("Prodaja karte") {

					@Override
					public void izvrsi() {
						KartaUI.dodavanje();
					}

				}, new FunkcionalnaStavkaMenija("Izvestavanje") {

					@Override
					public void izvrsi() {
						IzvestavanjeUI.izvestavanje();
					}

				} });
	}

}
