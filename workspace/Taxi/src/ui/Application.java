package ui;

import java.sql.Connection;
import java.sql.DriverManager;
import dao.VoziloDAO;
import dao.PozivDAO;
import dao.impl.DatabaseVoziloDAO;
import util.Meni;
import util.Meni.FunkcionalnaStavkaMenija;
import util.Meni.IzlaznaStavkaMenija;
import util.Meni.StavkaMenija;
import dao.impl.DatabasePozivDAO;

public class Application {

	private static void initDatabase() throws Exception {
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/taxi?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Europe/Belgrade",
				"root", "root" );

		VoziloDAO voziloDAO = new DatabaseVoziloDAO(conn);
		VoziloUI.setVoziloDAO(voziloDAO);

		//PozivDAO pozivDAO = new DatabasePozivDAO(conn);
		//PozivUI.setPozivDAO(pozivDAO);

	}

	static {
		try {
			initDatabase();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Greska pri povezivanju sa izvorom podataka!");

			System.exit(1);
		}
	}

	public static void main(String[] args) {
		Meni.pokreni("Taxi", new StavkaMenija[] { new IzlaznaStavkaMenija("Izlaz"),
				new FunkcionalnaStavkaMenija("Prikaz svih vozila") {

					@Override
					public void izvrsi() {
						VoziloUI.prikazSvih();
					}

				}, new FunkcionalnaStavkaMenija("Prikaz jednog vozila sa svim pozivima") {

					@Override
					public void izvrsi() {
						VoziloUI.prikaz();
					}

				}, new FunkcionalnaStavkaMenija("Dodavanje poziva") {

					@Override
					public void izvrsi() {
						//PozivUI.dodavanje();
					}

				}, new FunkcionalnaStavkaMenija("Izvestaj") {

					@Override
					public void izvrsi() {
						//IzvestajUI.izvestaj();
					}

				} });

	}

}
